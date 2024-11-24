import UIKit

struct RegisterFactory {
    func makeRegisterController(coordinator: RegisterViewControllerCoordinator, viewModel: RegisterViewModel) -> RegisterViewController {
        return RegisterViewController(coordinator: coordinator, viewModel: viewModel)
    }
}


