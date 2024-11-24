import UIKit

enum RegisterError: Error {
    case userExist
}

final class RegisterNetworkManager {
    static let shared = RegisterNetworkManager()
    let url = URL(string: "localhost:8080/register")
    
    
    private init() {}
    
    func postNewUser(registerModel: RegisterModel, completion: @escaping (Result<String, Error>) -> Void) {
        guard let url = self.url else { return }
        var request = URLRequest(url: url)
        
        request.httpMethod = "POST"
        
        let data = try! JSONEncoder().encode(registerModel)
        request.httpBody = data
        
        request.setValue(
            "application/json",
            forHTTPHeaderField: "Content-Type"
        )
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if response == nil {
                completion(.success(registerModel.name))
            } else {
                let statusCode = (response as! HTTPURLResponse).statusCode
                
                if statusCode == 200 {
                    completion(.success(registerModel.name))
                } else {
                    completion(.failure(RegisterError.userExist))
                }
            }
        }
        task.resume()
    }
}


