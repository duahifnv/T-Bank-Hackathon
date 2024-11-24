import UIKit

struct LogInFactory {
    let appDIContainer: AppDIContainer?
    
    func makeLogInViewController(coordinator: LogInViewControllerCoordinator) -> UIViewController {
        LogInViewController(coordinator: coordinator)
    }
    func makeRegisterCoordinator(navigation: UINavigationController, delegate: RegisterViewControllerCoordinatorDelegate) -> RegisterCoordinator {
        let registerFactory = RegisterFactory()
        return RegisterCoordinator(navigation: navigation, factory: registerFactory, delegate: delegate)
    }
}


