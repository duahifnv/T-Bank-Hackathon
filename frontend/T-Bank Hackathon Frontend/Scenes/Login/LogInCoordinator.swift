import UIKit

protocol LoginCoordinatorDelegate: AnyObject {
    func didFinishLogin()
}

final class LogInCoordinator: Coordinator {

    var navigation: UINavigationController
    var factory: LogInFactory
    var registerFactory: RegisterFactory
    weak var delegate: LoginCoordinatorDelegate?
    var delegateRegister: RegisterViewControllerCoordinatorDelegate
    
    var currentCoordinator: Coordinator?
    
    init(navigation: UINavigationController, factory: LogInFactory, delegate: LoginCoordinatorDelegate, registerFactory: RegisterFactory, registerDelegate: RegisterViewControllerCoordinatorDelegate) {
        self.navigation = navigation
        self.factory = factory
        self.delegate = delegate
        self.registerFactory = registerFactory
        self.delegateRegister = registerDelegate
    }
    
    func start() {
        let controller = factory.makeLogInViewController(coordinator: self)
        navigation.pushViewController(controller, animated: true)
    }
    func showRegisterController() {
        let registerCoordinator = self.factory.makeRegisterCoordinator(navigation: self.navigation, delegate: delegateRegister)
        let registerViewModel = RegisterViewModel()
        let registerViewController = self.registerFactory.makeRegisterController(coordinator: registerCoordinator, viewModel: registerViewModel)
        
        self.currentCoordinator = registerCoordinator
        self.navigation.pushViewController(registerViewController, animated: true)
    }
}

//MARK: - LogInViewControllerCoordinator

extension LogInCoordinator: LogInViewControllerCoordinator {
    func didFinish() {
        delegate?.didFinishLogin()
    }
    func showRegister() {
        self.showRegisterController()
    }
}



