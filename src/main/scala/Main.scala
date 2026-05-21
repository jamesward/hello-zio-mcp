import com.jamesward.ziohttp.mcp.*
import com.jamesward.ziohttp.mcp.auth.*
import zio.*
import zio.http.*
import zio.schema.*

case class GreetInput(name: String) derives Schema

object Main extends ZIOAppDefault:
  // Local bind port. Heroku and similar PaaSes inject $PORT.
  private val port = sys.env.get("PORT").map(_.toInt).getOrElse(8080)

  private val greetTool = McpTool("greet")
    .description("Says hello to someone by name")
    .handleWithContext: (input: GreetInput, ctx: McpToolContext) =>
      val caller = ctx.principal.flatMap(_.subject).getOrElse("anonymous")
      ZIO.succeed(s"Hello, ${input.name}! (signed in as $caller)")

  def run =
    val program =
      for
        verifier <- TokenVerifier.discoverJwks(issuer = "https://login.jamesward.dev")
        server = McpServer("hello-mcp", "1.0.0")
          .auth(McpAuth(
            authorizationServers = NonEmptyChunk(AuthorizationServer("https://login.jamesward.dev")),
            verifier = verifier,
          ))
          .tool(greetTool)
        _ <- Console.printLine(s"hello-mcp listening on 0.0.0.0:$port)")
        _ <- Server.serve(server.statelessRoutes)
      yield ()
    program.provide(
      Server.defaultWith(_.binding("0.0.0.0", port)),
      Client.default,
    )
