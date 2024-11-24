import UIKit

final class RegisterCoordinator: Coordinator {
    // MARK: - Properties
    var navigation: UINavigationController
    let factory: RegisterFactory
    let delegate: RegisterViewControllerCoordinatorDelegate
    
    var activeUser: RegisterModel?
    
    init(navigation: UINavigationController, factory: RegisterFactory, delegate: RegisterViewControllerCoordinatorDelegate) {
        self.navigation = navigation
        self.factory = factory
        self.delegate = delegate
    }
    
    // MARK: - Methods
    func start() {
        let viewModel = RegisterViewModel()
        let vc = factory.makeRegisterController(coordinator: self, viewModel: viewModel)
        self.navigation.pushViewController(vc, animated: true)
    }
    func showHomePage(name: String) {
        
        let vc = HomeViewController(coordinator: self, newUser: activeUser!)
        self.navigation.viewControllers = [vc]
    }
}

extension RegisterCoordinator: RegisterViewControllerCoordinator {
    func didFinish(user: RegisterModel) {
        self.activeUser = user
    }
}

extension RegisterCoordinator: RegisterViewControllerCoordinatorDelegate {
    func didFinishRegister(user: RegisterModel) {
        self.delegate.didFinishRegister(user: user)
    }
    
    
}


