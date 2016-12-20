package quiz

import akka.http.scaladsl.server.Route
import quiz.resources.QuestionResource
import quiz.services.QuestionService

import scala.concurrent.ExecutionContext

/**
  * Created by stiwari on 12/20/2016 AD.
  */
trait RestInterface extends Resources {

  implicit def executionContext: ExecutionContext

  lazy val questionService = new QuestionService

  val routes: Route = questionRoutes

}

trait Resources extends QuestionResource