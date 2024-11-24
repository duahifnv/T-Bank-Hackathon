import UIKit

protocol LogInViewControllerCoordinator: AnyObject {
    func didFinish()
    func showRegister()
}

final class LogInViewController: UIViewController {
    // MARK: - GUI
    private let mainView = MainView()
    
    private let loginTextField = LoginTextField()
    private let passwordTextField = LoginTextField()
    
    private let mainButton = LoginMainButton()
    
    private weak var coordinator: LogInViewControllerCoordinator?
    
    let signUpStackView = SignUpTransitionStackView()
    
    private let logInButton: UIButton = {
        let button = UIButton(type: .system)
        var configuration = UIButton.Configuration.filled()
        configuration.title = "LogIn"
        button.configuration = configuration
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    init(coordinator: LogInViewControllerCoordinator?) {
        self.coordinator = coordinator
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
        
        configureBackBarButton()
        
        configureLoginTextField()
        configureLoginTextFieldLayout()
        configurePasswordTextField()
        configurePasswordTextFieldLayout()
        
        configureMainButton()
        configureMainButtonLayout()
        
        configureSignUpStackView()
        configureSignUpStackViewLayout()
    }
    
    private func setupUI() {
        view.backgroundColor = .systemBackground
        
        view.addSubview(logInButton)
        
        logInButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        logInButton.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
    }
    
    private func configureBackBarButton() {
        let backBarButton = UIBarButtonItem(title: "Назад", style: .plain, target: nil, action: nil)
        backBarButton.tintColor = .black
        self.navigationItem.backBarButtonItem = backBarButton
    }
}

// MARK: - Configure Background
private extension LogInViewController {
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
private extension LogInViewController {
    func configureMainView() {
        self.mainView.mainTitle.text = "Войти в аккаунт"
        self.mainView.secondTitle.text = "Введите ваш номер телефона и пароль"
    }
    func configureMainViewLayout() {
        self.view.addSubview(mainView)
        mainView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.mainView.widthAnchor.constraint(equalTo: self.view.widthAnchor, multiplier: 0.85),
            self.mainView.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.55),
            self.mainView.centerYAnchor.constraint(equalTo: self.view.centerYAnchor),
            self.mainView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
    }
}

// MARK: - Configure LoginTextField
private extension LogInViewController {
    func configureLoginTextField() {
        self.loginTextField.placeholder = "Номер телефона"
        self.loginTextField.keyboardType = .phonePad
        self.loginTextField.delegate = self
    }
    func configureLoginTextFieldLayout() {
        self.mainView.addSubview(loginTextField)
        loginTextField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.loginTextField.topAnchor.constraint(equalTo: self.mainView.secondTitle.bottomAnchor, constant: 28),
            self.loginTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.loginTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.loginTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17
                                                       )
        ])
    }
}
// MARK: - Configure PasswordTextField
private extension LogInViewController {
    func configurePasswordTextField() {
        self.passwordTextField.placeholder = "Пароль"
        self.passwordTextField.textContentType = .oneTimeCode
        self.passwordTextField.isSecureTextEntry = true
    }
    func configurePasswordTextFieldLayout() {
        self.mainView.addSubview(passwordTextField)
        passwordTextField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.passwordTextField.topAnchor.constraint(equalTo: self.loginTextField.bottomAnchor, constant: 10),
            self.passwordTextField.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 16),
            self.passwordTextField.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -16),
            self.passwordTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}

// MARK: - Configure MainButton
private extension LogInViewController {
    func configureMainButton() {
        self.mainButton.setTitle("Войти", for: .normal)
    }
    func configureMainButtonLayout() {
        self.mainView.addSubview(mainButton)
        mainButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.mainButton.leadingAnchor.constraint(equalTo: self.mainView.leadingAnchor, constant: 20),
            self.mainButton.trailingAnchor.constraint(equalTo: self.mainView.trailingAnchor, constant: -20),
            self.mainButton.heightAnchor.constraint(equalTo: self.mainView.heightAnchor, multiplier: 1 / 9),
            self.mainButton.topAnchor.constraint(equalTo: self.passwordTextField.bottomAnchor, constant: 30)
        ])
    }
}

// MARK: - Configure SignUpStackView
private extension LogInViewController {
    func configureSignUpStackView() {
        self.signUpStackView.title.text = "У вас нет аккаунта?"
        self.signUpStackView.button.setTitle("Зарегистрироваться", for: .normal)
        self.signUpStackView.button.addTarget(self, action: #selector(mainButtonAction), for: .touchUpInside)
    }
    @objc func mainButtonAction() {
        print("Кнопка нажата")
        self.coordinator?.showRegister()
    }
    
    func configureSignUpStackViewLayout() {
        self.mainView.addSubview(signUpStackView
        )
        signUpStackView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.signUpStackView.bottomAnchor.constraint(equalTo: self.mainView.bottomAnchor, constant: -16),
            self.signUpStackView.centerXAnchor.constraint(equalTo: self.mainView.centerXAnchor)
        ])
    }
}

// MARK: - UITextFieldDelegate
extension LogInViewController: UITextFieldDelegate {
    func textFieldShouldClear(_ textField: UITextField) -> Bool {
        return true
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


