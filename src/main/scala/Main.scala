import com.jamesward.ziohttp.mcp.*
import zio.*
import zio.http.*
import zio.schema.*

case class GreetInput(name: String) derives Schema

val server = McpServer("hello-mcp", "1.0.0")
  .tool(
    McpTool("greet")
      .description("Says hello to someone by name")
      .handle: (input: GreetInput) =>
        ZIO.succeed(s"Hello, ${input.name}!")
  )

object Main extends ZIOAppDefault:
  def run =
    Server.serve(server.statelessRoutes).provide(Server.default)
