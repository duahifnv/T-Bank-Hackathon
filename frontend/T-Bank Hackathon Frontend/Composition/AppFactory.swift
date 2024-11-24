import UIKit

struct AppFactory {
    let appDIContainer: AppDIContainer?
    
    func makeLogInCoordinator(navigation: UINavigationController, delegate: LoginCoordinatorDelegate, registerDelegate: RegisterViewControllerCoordinatorDelegate) -> Coordinator {
        let logInFactory = LogInFactory(appDIContainer: appDIContainer)
        let registerFactory = RegisterFactory()
        return LogInCoordinator(navigation: navigation,
                                           factory: logInFactory,
                                           delegate: delegate,
                                registerFactory: registerFactory, registerDelegate: registerDelegate)
    }
    
    
    func makeHelperCoordinator(navigation: UINavigationController, auth: Auth) -> Coordinator {
        return HelperCoordinator(navigation: navigation, auth: auth)
    }
}


