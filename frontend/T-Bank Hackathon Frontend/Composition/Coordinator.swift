import UIKit

protocol Coordinator: AnyObject {
    var navigation: UINavigationController { get set }
    func start()
}



