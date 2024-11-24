import UIKit

class AppCoordinator: Coordinator {
    var navigation: UINavigationController
    var window: UIWindow?
    var factory: AppFactory?
    var auth: Auth?
    
    private var logInCoordinator: Coordinator?
    private var mainTabBarCoordinator: Coordinator?
    
    init(navigation: UINavigationController, window: UIWindow?, factory: AppFactory?, auth: Auth?) {
        self.navigation = navigation
        self.window = window
        self.factory = factory
        self.auth = auth
    }
    
    func start() {
        configWindow()
        startSomeCoordinator()
    }
    
    private func configWindow() {
        window?.rootViewController = navigation
        window?.makeKeyAndVisible()
    }
    
    private func startSomeCoordinator() {
        startLoginCoordinator()
    }
    
    private func startLoginCoordinator() {
        logInCoordinator = factory?.makeLogInCoordinator(navigation: navigation,
                                                             delegate: self, registerDelegate: self)
        logInCoordinator?.start()
    }
    
    private func startHelperCoordinator() {
        guard let auth = auth else { return }
        let helperCoordinator = self.factory?.makeHelperCoordinator(navigation: self.navigation, auth: auth)
        helperCoordinator?.start()
    }
}

//MARK: - LoginCoordinatorDelegate

extension AppCoordinator: LoginCoordinatorDelegate {
    func didFinishLogin() {
        navigation.viewControllers = []
        logInCoordinator = nil
        startSomeCoordinator()
    }
}


// MARK: - RegisterViewControllerCoordinatorDelegate
extension AppCoordinator: RegisterViewControllerCoordinatorDelegate {
    func didFinishRegister(user: RegisterModel) {
        self.auth?.currentUser = user
        
        self.logInCoordinator = nil
        
        self.navigation.viewControllers = []
        
        
        startHelperCoordinator()
    }
}


