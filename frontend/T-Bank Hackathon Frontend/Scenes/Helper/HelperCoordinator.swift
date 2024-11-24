import UIKit

final class HelperCoordinator: Coordinator {
    var navigation: UINavigationController
    var auth: Auth
    
    init(navigation: UINavigationController, auth: Auth) {
        self.navigation = navigation
        self.auth = auth
    }
    
    func start() {
        guard let user = self.auth.currentUser else { return }
        let vc = HelperViewController(coordinator: self, fullName: user.name + " " + user.lastName)
        self.navigation.pushViewController(vc, animated: true)
    }
}

// MARK: - AddViewControllerCoordinator
extension HelperCoordinator: HelperViewControllerCoordinatorDelegate {
    func showAddController() {
        let viewModel = AddViewModel()
        let vc = AddViewController(viewModel: viewModel)
        self.navigation.present(vc, animated: true)
    }
}


