import UIKit

// MARK: - HomeViewController
final class HomeViewController: UIViewController {
    // MARK: - Properties
    let newUser: RegisterModel
    var coordinator: RegisterViewControllerCoordinatorDelegate?
    
    init(coordinator: RegisterViewControllerCoordinatorDelegate, newUser: RegisterModel) {
        self.coordinator = coordinator
        self.newUser = newUser
        super.init(nibName: nil, bundle: nil)
    }
    // MARK: - GUI
    private let personLogo = UIImageView()
    private let greetingLabel = UILabel()
    private let nameLabel = UILabel()
    private let abilitiesLabel = UILabel()
    
    private let abilityView1 = HomeDescriptionView(title: "Анализ финансов", text: "Получите детальный обзор ваших финансов и найдите возможности для экономии", color: .systemYellow)
    private let abilityView2 = HomeDescriptionView(title: "Бюджетирование", text: "Создайте индивидуальный бюджет, который поможет достичь ваших финансовых целей", color: .bottomLogin)
    
    private let continueButton = LoginMainButton()
    
    // MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = .systemGray6
        
        
        configurePersonLogo()
        configurePersonLogoLayout()
        
        configureGreetingLabel()
        configureGreetingLabelLayout()
        
        configureNameLabel()
        configureNameLabelLayout()
        
        configureAbilitiesLabel()
        configureAbilitiesLabelLayout()
        
        configureAbilityView()
        configureAbilityViewLayout()
        
        configureAbilityView2()
        configureAbilityViewLayout2()
        
        configureContinueButton()
        configureContinueButtonLayout()
    }
    
    // MARK: - Init
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

// MARK: - Configure PersonLogo
private extension HomeViewController {
    func configurePersonLogo() {
        self.personLogo.image = UIImage.ava
        self.personLogo.contentMode = .scaleAspectFit
    }
    func configurePersonLogoLayout() {
        self.view.addSubview(personLogo)
        personLogo.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.personLogo.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 28),
            self.personLogo.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.personLogo.heightAnchor.constraint(equalToConstant: 70),
            self.personLogo.widthAnchor.constraint(equalToConstant: 70)
        ])
    }
}

// MARK: - Configure GreetingLabel
private extension HomeViewController {
    func configureGreetingLabel() {
        self.greetingLabel.text = "Добро пожаловать,"
        self.greetingLabel.font = UIFont.systemFont(ofSize: 26, weight: .heavy)
    }
    func configureGreetingLabelLayout() {
        self.view.addSubview(greetingLabel)
        greetingLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.greetingLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.greetingLabel.topAnchor.constraint(equalTo: self.personLogo.bottomAnchor, constant: 20)
        ])
    }
}

// MARK: - Configure NameLabel
private extension HomeViewController {
    func configureNameLabel() {
        self.nameLabel.text = "\(newUser.name)!"
        self.nameLabel.textColor = .systemYellow
        self.nameLabel.font = UIFont.systemFont(ofSize: 26, weight: .heavy)
    }
    func configureNameLabelLayout() {
        self.view.addSubview(nameLabel)
        nameLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.nameLabel.topAnchor.constraint(equalTo: self.greetingLabel.bottomAnchor, constant: 4),
            self.nameLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
    }
}

// MARK: - Configure AbilitiesLabel
private extension HomeViewController {
    func configureAbilitiesLabel() {
        self.abilitiesLabel.text = "Возможности помощника:"
        self.abilitiesLabel.font = UIFont.systemFont(ofSize: 23, weight: .heavy)
    }
    func configureAbilitiesLabelLayout() {
        self.view.addSubview(abilitiesLabel)
        abilitiesLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.abilitiesLabel.topAnchor.constraint(equalTo: nameLabel.bottomAnchor, constant: 30),
            self.abilitiesLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
        ])
    }
}

// MARK: - Configure AbilityView1
private extension HomeViewController {
    func configureAbilityView() {
        
    }
    func configureAbilityViewLayout() {
        self.view.addSubview(abilityView1)
        abilityView1.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.abilityView1.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 34),
            self.abilityView1.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -34),
            self.abilityView1.topAnchor.constraint(equalTo: self.abilitiesLabel.bottomAnchor, constant: 30)
        ])
    }
}

// MARK: - Configure AbilityView2
private extension HomeViewController {
    func configureAbilityView2() {
        
    }
    func configureAbilityViewLayout2() {
        self.view.addSubview(abilityView2)
        abilityView2.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.abilityView2.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 34),
            self.abilityView2.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -34),
            self.abilityView2.topAnchor.constraint(equalTo: self.abilityView1.bottomAnchor, constant: 30)
        ])
    }
}

// MARK: - Configure ContinueButton
private extension HomeViewController {
    func configureContinueButton() {
        self.continueButton.setTitle("Продолжить", for: .normal)
        self.continueButton.addTarget(self, action: #selector(continueButtonAction), for: .touchUpInside)
    }
    @objc func continueButtonAction() {
        print(coordinator)
        self.coordinator?.didFinishRegister(user: newUser)
    }
    func configureContinueButtonLayout() {
        self.view.addSubview(continueButton)
        continueButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.continueButton.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: -34),
            self.continueButton.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 34),
            self.continueButton.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -34),
            self.continueButton.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 17)
        ])
    }
}


