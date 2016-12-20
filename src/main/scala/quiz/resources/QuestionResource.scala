package quiz.resources

import akka.http.scaladsl.server.Route
import quiz.entities.{Question, QuestionUpdate}
import quiz.routing.MyResource
import quiz.services.QuestionService

import scala.concurrent.ExecutionContext

/**
  * Created by stiwari on 12/20/2016 AD.
  */
trait QuestionResource extends MyResource {

  val questionService: QuestionService

  def questionRoutes: Route = pathPrefix("questions") {
    pathEnd {
      post {
        entity(as[Question]) { question =>
          completeWithLocationHeader(
            resourceId = questionService.createQuestion(question),
            ifDefinedStatus = 201, ifEmptyStatus = 409)
        }
      }
    } ~
      path(Segment) { id =>
        get {
          complete(questionService.getQuestion(id))
        } ~
          put {
            entity(as[QuestionUpdate]) { update =>
              complete(questionService.updateQuestion(id, update))
            }
          } ~
          delete {
            complete(questionService.deleteQuestion(id))
          }
      }

  }
}
