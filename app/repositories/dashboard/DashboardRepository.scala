package repositories.dashboard

import java.io.File

import ftd_api.yaml.{Catalog, Dashboard, DashboardIframes, Success, UserStory}

import scala.concurrent.Future

/**
  * Created by ale on 14/04/17.
  */
trait DashboardRepository {
  def save(upFile: File, tableName: String, fileType: String): Success
  def update(upFile: File, tableName: String, fileType: String): Success
  def tables(): Seq[Catalog]
  def iframes(metaUser: String): Future[Seq[DashboardIframes]]
  def iframesByOrg(user: String,org: String): Future[Seq[DashboardIframes]]
  def dashboards(groups: List[String], status: Option[Int]): Seq[Dashboard]
  def dashboardById(groups: List[String], id: String): Dashboard
  def saveDashboard(dashboard: Dashboard, user: String): Success
  def deleteDashboard(dashboardId: String): Success
  def stories(groups: List[String], status: Option[Int], page: Option[Int], limit: Option[Int]): Seq[UserStory]
  def storyById(groups: List[String], id: String): UserStory
  def publicStoryById(id: String): UserStory
  def saveStory(story: UserStory, user: String): Success
  def deleteStory(storyId: String): Success
  def storiesPublic(status: Option[Int]): Seq[UserStory]
  def dashboardsPublic(status: Option[Int]): Seq[Dashboard]
  def publicDashboardById(id: String): Dashboard
}

object DashboardRepository {
  def apply(config: String): DashboardRepository = config match {
    case "dev" => new DashboardRepositoryDev
    case "prod" => new DashboardRepositoryProd
  }
}

trait DashboardRepositoryComponent {
  val dashboardRepository :DashboardRepository
}


