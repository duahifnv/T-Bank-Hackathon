import UIKit

protocol RegisterViewControllerCoordinator: AnyObject {
    func didFinish(user: RegisterModel)
    func showHomePage(name: String)
}

protocol RegisterViewControllerCoordinatorDelegate: AnyObject {
    func didFinishRegister(user: RegisterModel)
}

final class RegisterViewController: UIViewController {
    // MARK: - GUI
    private let mainView = MainView()
    
    private let nameTextField = LoginTextField()
    private let lastNameTextField = LoginTextField()
    private let loginTextField = LoginTextField()
    private let passwordTextField = LoginTextField()
    private let anotherPasswordTextField = LoginTextField()
    
    private let mainButton = LoginMainButton()
    
    private weak var coordinator: RegisterViewControllerCoordinator?
    private let viewModel: RegisterViewModel
    
    let signUpStackView = SignUpTransitionStackView()
    
    private let logInButton: UIButton = {
        let button = UIButton(type: .system)
        var configuration = UIButton.Configuration.filled()
        configuration.title = "Register"
        button.configuration = configuration
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    init(coordinator: RegisterViewControllerCoordinator,
         viewModel: RegisterViewModel) {
        self.coordinator = coordinator
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupUI()
        
        setBackgroundColor()
        setBackgroundGradient()
        
        configureMainView()
        configureMainViewLayout()
        
        configureNameTextField()
        configureNameTextFieldLayout()
        
        configureLastNameTextField()
        configureLastNameTextFieldLayout()
        
        configureRegisterTextField()
        configureRegisterTextFieldLayout()
        
        configurePasswordTextField()
        configurePasswordTextFieldLayout()
        
        configureCheckPasswordTextField()
        configureCheckPasswordTextFieldLayout()
        
        configureMainButton()
        configureMainButtonLayout()
    }
    
    private func setupUI() {
        view.backgroundColor = .systemBackground
        
        view.addSubview(logInButton)
        
        logInButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        logInButton.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
    }
}

// MARK: - Configure Background
private extension RegisterViewController {
    func setBackgroundColor() {
        self.view.backgroundColor = .white
    }
    func setBackgroundGradient() {
        let gradientLayer = CAGradientLayer()
        let topCollors = [UIColor.topLogin.withAlphaComponent(0.5).cgColor, UIColor.bottomLogin.withAlphaComponent(0.5).cgColor]
        gradientLayer.colors = topCollors
        gradientLayer.startPoint = CGPoint(x: 0.0, y: 0.0)
        gradientLayer.endPoint = CGPoint(x: 1.0, y: 1.0)
        
        gradientLayer.locations = [0, 1]
        
        gradientLayer.frame = view.bounds
        
        view.layer.insertSublayer(gradientLayer, at: 0)
    }
}

// MARK: - Configure MainView
private extension RegisterViewController {
    func configureMainView() {
        self.mainView.mainTitle.text = "Создать аккаунт"
        self.mainView.secondTitle.text = "Введите ваши данные для регистрации"
    }
    func configureMainViewLayout() {
        self.view.addSubview(mainView)
        mainView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.mainView.widthAnchor.constraint(equalTo: self.view.widthAnchor, multiplier: 0.85),
            self.mainView.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.7),
            self.mainView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor),
            self.mainView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
    }
}

// MARK: - Configure NameTextField
// ввод только Букв
private extension RegisterViewController {
    func configureNameTextField() {
        self.nameTextField.placeholder = "Имя"
        self.nameTextField.delegate = self
    }
    func configureNameTextFieldLayout() {
        self.mainView.addSubview(nameTextField)
        nameTextField.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.nameTextField.topAnchor.constraint(equalTo: self.mainView.secondTitle.bottomAnchor, constant: 28),
            self.nameTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.nameTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.nameTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}
// MARK: - Configure LastNameTextField
// ввод только Букв
private extension RegisterViewController {
    func configureLastNameTextField() {
        self.lastNameTextField.placeholder = "Фамилия"
        self.lastNameTextField.delegate = self
    }
    func configureLastNameTextFieldLayout() {
        self.mainView.addSubview(lastNameTextField)
        lastNameTextField.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.lastNameTextField.topAnchor.constraint(equalTo: self.nameTextField.bottomAnchor, constant: 8),
            self.lastNameTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.lastNameTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.lastNameTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}

// MARK: - Configure RegisterTextField
// скопировать из логина
private extension RegisterViewController {
    func configureRegisterTextField() {
        self.loginTextField.placeholder = "Номер телефона"
        self.loginTextField.delegate = self
        self.loginTextField.keyboardType = .phonePad
    }
    func configureRegisterTextFieldLayout() {
        self.mainView.addSubview(loginTextField)
        loginTextField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.loginTextField.topAnchor.constraint(equalTo: self.lastNameTextField.bottomAnchor, constant: 8),
            self.loginTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.loginTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.loginTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}
// MARK: - Configure PasswordTextField
// не менее 6 символов
private extension RegisterViewController {
    func configurePasswordTextField() {
        self.passwordTextField.placeholder = "Придумайте пароль"
        self.passwordTextField.delegate = self
        self.passwordTextField.isSecureTextEntry = true
        self.passwordTextField.textContentType = .oneTimeCode
    }
    func configurePasswordTextFieldLayout() {
        self.mainView.addSubview(passwordTextField)
        passwordTextField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.passwordTextField.topAnchor.constraint(equalTo: self.loginTextField.bottomAnchor, constant: 8),
            self.passwordTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.passwordTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.passwordTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}

// MARK: - Configure CheckPasswordTextField
// должен быть равен пред полю
private extension RegisterViewController {
    func configureCheckPasswordTextField() {
        self.anotherPasswordTextField.placeholder = "Подтвердите пароль"
        self.anotherPasswordTextField.delegate = self
        self.anotherPasswordTextField.isSecureTextEntry = true
        self.anotherPasswordTextField.textContentType = .oneTimeCode
    }
    func configureCheckPasswordTextFieldLayout() {
        self.mainView.addSubview(anotherPasswordTextField)
        anotherPasswordTextField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.anotherPasswordTextField.topAnchor.constraint(equalTo: self.passwordTextField.bottomAnchor, constant: 8),
            self.anotherPasswordTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.anotherPasswordTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.anotherPasswordTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}

// MARK: - Configure MainButton
private extension RegisterViewController {
    func configureMainButton() {
        self.mainButton.setTitle("Зарегистрироваться", for: .normal)
        self.mainButton.addTarget(self, action: #selector(mainButtonAction), for: .touchUpInside)
    }
    func checkNameTextField() -> Bool {
        guard var text = self.nameTextField.text else { return false }
        text = text.replacingOccurrences(of: " ", with: "")
        if text.isEmpty {
            return false
        }
        
        return true
    }
    func checkLastNameTextField() -> Bool {
        guard var text = self.lastNameTextField.text else { return false }
        text = text.replacingOccurrences(of: " ", with: "")
        if text.isEmpty {
            return false
        }
        
        return true
    }
    func checkLoginTextField() -> Bool {
        guard let text = self.loginTextField.text else { return false }
        if text.count < 12 {
            return false
        }
        
        return true
    }
    func checkPasswordTextField() -> Bool {
        guard let text = self.passwordTextField.text else { return false }
        if text.count < 6 {
            return false
        }
        
        return true
    }
    
    func checkAnotherPasswordTextField() -> Bool {
        guard let text = self.anotherPasswordTextField.text else { return false }
        guard let anotherText = self.passwordTextField.text else { return false }
        if text != anotherText  || text.isEmpty {
            return false
        }
        
        return true
    }
    @objc func mainButtonAction() {
        if !checkNameTextField() {
            self.nameTextField.layer.borderColor = UIColor.red.cgColor
            return
        } else {
            self.nameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        }
        
        if !checkLastNameTextField() {
            self.lastNameTextField.layer.borderColor = UIColor.red.cgColor
            return
        } else {
            self.lastNameTextField.layer.borderColor = UIColor.systemGray5.cgColor
        }
        
        if !checkLoginTextField() {
            self.loginTextField.layer.borderColor = UIColor.red.cgColor
            return
        } else {
            self.loginTextField.layer.borderColor = UIColor.systemGray5.cgColor
        }
        
        if !checkPasswordTextField() {
            self.passwordTextField.layer.borderColor = UIColor.red.cgColor
            return
        } else {
            self.passwordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        }
        
        if !checkAnotherPasswordTextField() {
            self.anotherPasswordTextField.layer.borderColor = UIColor.red.cgColor
            return
        } else {
            self.anotherPasswordTextField.layer.borderColor = UIColor.systemGray5.cgColor
            self.passwordTextField.layer.borderColor = UIColor.systemGray5.cgColor
        }
        
        let newUser = self.getRegisterValues()
        
        // send post request
        let newUserModel = RegisterModel(name: newUser.0, lastName: newUser.1, login: newUser.2, password: newUser.3)
        
        self.viewModel.postUser(name: newUserModel.name, lastName: newUserModel.lastName, login: newUserModel.login, password: newUserModel.password) { result in
            switch result {
            case true:
                DispatchQueue.main.async {
                    self.mainButton.backgroundColor = .systemYellow
                    self.coordinator?.didFinish(user: newUserModel)
                    self.coordinator?.showHomePage(name: newUserModel.name)
                    
                }
            default:
                self.mainButton.backgroundColor = .systemRed
            }
        }
    }
    
    func getRegisterValues() -> (String, String, String, String ) {
        (self.nameTextField.text!, self.lastNameTextField.text!, self.loginTextField.text!, self.passwordTextField.text!)
    }
    
    
    func configureMainButtonLayout() {
        self.mainView.addSubview(mainButton)
        mainButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.mainButton.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 20),
            self.mainButton.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -20),
            self.mainButton.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17),
            self.mainButton.topAnchor.constraint(equalTo: self.anotherPasswordTextField.bottomAnchor, constant: 30)
        ])
    }
}

// MARK: - UITextFieldDelegate
extension RegisterViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField {
        case nameTextField:
            lastNameTextField.becomeFirstResponder()
        case lastNameTextField:
            loginTextField.becomeFirstResponder()
        case loginTextField:
            passwordTextField.becomeFirstResponder()
        case passwordTextField:
            anotherPasswordTextField.becomeFirstResponder()
        default:
            self.view.endEditing(true)
        }
    }
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        // Проверяем текущий текст
        switch textField {
        case loginTextField:
            guard let currentText = textField.text else { return true }
            
            // Запрещаем удаление первых двух символов
            if range.location < 2 && string.isEmpty {
                return false
            }
            
            // Вычисляем новый текст после предполагаемого изменения
            let newText = (currentText as NSString).replacingCharacters(in: range, with: string)
            
            // Ограничиваем длину текста 12 символами
            if newText.count > 12 {
                return false
            }
        default:
            break
        }
        return true
    }
}


