import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    var appCoordinator: Coordinator?
    var appFactory: AppFactory?
    var appDIContainer: AppDIContainer?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        appDIContainer = AppDIContainer()
        appFactory = AppFactory(appDIContainer: appDIContainer)
        appCoordinator = AppCoordinator(navigation: UINavigationController(),
                                        window: window,
                                        factory: appFactory,
                                        auth: appDIContainer?.auth)
        appCoordinator?.start()
    }
}



