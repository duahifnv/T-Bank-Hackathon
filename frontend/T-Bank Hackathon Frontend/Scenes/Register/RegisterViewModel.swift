import Foundation

final class RegisterViewModel {
    let networkManager = RegisterNetworkManager.shared
    var newUser: RegisterModel?
    
    func postUser(name: String, lastName: String, login: String, password: String, completion: @escaping (Bool) -> Void) {
        let registerModel = RegisterModel(name: name, lastName: lastName, login: login, password: password)
        
        self.networkManager.postNewUser(registerModel: registerModel) { result in
            switch result {
            case .success(let success):
                self.newUser = registerModel
                completion(true)
            case .failure(let failure):
                completion(false)
            }
        }
    }
}


