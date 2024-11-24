import UIKit

protocol HelperViewControllerCoordinatorDelegate {
    func showAddController()
}

// MARK: - HelperViewController
final class HelperViewController: UIViewController {
    // MARK: - Properties
    var money: Double = 0
    var fullName: String
    
    var coordinator: HelperViewControllerCoordinatorDelegate
    
    init(coordinator: HelperViewControllerCoordinatorDelegate, fullName: String) {
        self.coordinator = coordinator
        self.fullName = fullName
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    
    // MARK: - GUI
    private let titleLabel = UILabel()
    private let userImageView = UIImageView()
    private let cardView = UIView()
    
    private let cardLabelsStackView = UIStackView()
    private let cardMoneyStatusLabel = UILabel()
    private let cardMoneyCountLabel = UILabel()
    private let cardMoneyOwnerLabel = UILabel()
    
    private let logoImageView = UIImageView()
    
    private let historyExpensesView = UIView()
    private let historyTitleLabel = UILabel()
    private let historyAddButton = UIButton()
    private let historyTableView = UITableView()
    
    // MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = .systemGray6
        
        configureNavigationBar()
        
        configureTitleLabel()
        configureTitleLabelLayout()
        
        configureUserImageView()
        configureUserImageViewLayout()
        
        configureCardView()
        configureCardViewLayout()
        
        configureCardLabelsStackView()
        configureCardLabelsStackViewLayout()
        
        configureCardMoneyStatusLabel()
        configureCardMoneyStatusLabelLayout()
        
        configureCardMoneyCountLabel()
        configureCardMoneyCountLabelLayout()
        
        configureCardMoneyOwnerLabel()
        configureCardMoneyOwnerLabelLayout()
        
        configureLogoImageView()
        configureLogoImageViewLayout()
        
        configureHistoryExpensesView()
        
        configureHistoryExpensesViewLayout()
        
        configureHistoryTableView()
        configureHistoryTableViewLayout()
    }
    // MARK: - Private Methods
    private func configureNavigationBar() {
        self.navigationController?.isNavigationBarHidden = true
    }
}

// MARK: - Configure TitleLabel
private extension HelperViewController {
    func configureTitleLabel() {
        self.titleLabel.text = "Помощник"
        self.titleLabel.font = UIFont.systemFont(ofSize: 30, weight: .bold)
    }
    func configureTitleLabelLayout() {
        self.view.addSubview(titleLabel)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.titleLabel.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 20),
            self.titleLabel.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 40)
        ])
    }
}

// MARK: - Configure UserImageView
private extension HelperViewController {
    func configureUserImageView() {
        self.userImageView.image = UIImage.ava
    }
    func configureUserImageViewLayout() {
        self.view.addSubview(userImageView)
        userImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.userImageView.bottomAnchor.constraint(equalTo: self.titleLabel.bottomAnchor),
            self.userImageView.heightAnchor.constraint(equalToConstant: 50),
            self.userImageView.widthAnchor.constraint(equalToConstant: 50),
            self.userImageView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -32)
        ])
    }
}

// MARK: - Configure CardView
private extension HelperViewController {
    func configureCardView() {
        self.cardView.layer.cornerRadius = 20
        self.cardView.clipsToBounds = true
        self.cardView.backgroundColor = .card
        self.cardView.layer.borderColor = UIColor.systemGray5.cgColor
        self.cardView.layer.borderWidth = 1
    }
    func configureCardViewLayout() {
        self.view.addSubview(cardView)
        cardView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.cardView.widthAnchor.constraint(equalTo: self.view.widthAnchor, multiplier: 0.85),
            self.cardView.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 6),
            self.cardView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.cardView.topAnchor.constraint(equalTo: self.titleLabel.bottomAnchor, constant: 25)
            
        ])
    }
}


// MARK: - Configure CardLabelsStackView
private extension HelperViewController {
    func configureCardLabelsStackView() {
        self.cardLabelsStackView.axis = .vertical
        self.cardLabelsStackView.distribution = .equalCentering
    }
    func configureCardLabelsStackViewLayout() {
        self.cardView.addSubview(cardLabelsStackView)
        cardLabelsStackView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.cardLabelsStackView.leadingAnchor.constraint(equalTo: self.cardView.leadingAnchor, constant: 20),
            self.cardLabelsStackView.topAnchor.constraint(equalTo: self.cardView.topAnchor, constant: 30),
            self.cardLabelsStackView.bottomAnchor.constraint(equalTo: self.cardView.bottomAnchor, constant: -30)
        ])
    }
}
// MARK: - Configure CardMoneyStatusLabel
private extension HelperViewController {
    func configureCardMoneyStatusLabel() {
        self.cardMoneyStatusLabel.text = "Текущий баланс на счете"
        self.cardMoneyStatusLabel.font = UIFont.systemFont(ofSize: 16, weight: .medium)
        self.cardMoneyStatusLabel.textColor = .systemGray4
    }
    func configureCardMoneyStatusLabelLayout() {
        self.cardLabelsStackView.addArrangedSubview(cardMoneyStatusLabel)
    }
}

// MARK: - Configure CardMoneyCountLabel
private extension HelperViewController {
    func configureCardMoneyCountLabel() {
        self.cardMoneyCountLabel.text = money.formatted() + "₽"
        self.cardMoneyCountLabel.font = UIFont.systemFont(ofSize: 20, weight: .heavy)
        self.cardMoneyCountLabel.textColor = .white
    }
    func configureCardMoneyCountLabelLayout() {
        self.cardLabelsStackView.addArrangedSubview(cardMoneyCountLabel)
    }
}

// MARK: - Configure CardMoneyOwnerLabel
private extension HelperViewController {
    func configureCardMoneyOwnerLabel() {
        self.cardMoneyOwnerLabel.text = fullName
        self.cardMoneyOwnerLabel.font = UIFont.systemFont(ofSize: 16, weight: .medium)
        self.cardMoneyOwnerLabel.textColor = .systemGray4
    }
    func configureCardMoneyOwnerLabelLayout() {
        self.cardLabelsStackView.addArrangedSubview(cardMoneyOwnerLabel)
    }
}

// MARK: - Configure LogoImageView
private extension HelperViewController {
    func configureLogoImageView() {
        self.logoImageView.image = UIImage.logo
        self.logoImageView.layer.cornerRadius = 10
        self.logoImageView.layer.opacity = 0.6
        self.logoImageView.clipsToBounds = true
    }
    func configureLogoImageViewLayout() {
        self.cardView.addSubview(logoImageView)
        logoImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.logoImageView.trailingAnchor.constraint(equalTo: self.cardView.trailingAnchor, constant: -16),
            self.logoImageView.widthAnchor.constraint(equalToConstant: 55),
            self.logoImageView.heightAnchor.constraint(equalToConstant: 55),
            self.logoImageView.centerYAnchor.constraint(equalTo: self.cardLabelsStackView.centerYAnchor)
        ])
    }
}

// MARK: - Configure HistoryExpensesView
private extension HelperViewController {
    func configureHistoryExpensesView() {
        self.historyTitleLabel.text = "Последние операции"
        self.historyTitleLabel.font = UIFont.systemFont(ofSize: 25, weight: .bold)
        self.historyAddButton.setImage(UIImage(systemName: "plus"), for: .normal)
        self.historyAddButton.addTarget(self, action: #selector(historyAddButtonAction), for: .touchUpInside)
    }
    @objc func historyAddButtonAction() {
        self.coordinator.showAddController()
    }
    func configureHistoryExpensesViewLayout() {
        // tittle
        self.historyExpensesView.addSubview(historyTitleLabel)
        historyTitleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.historyTitleLabel.leadingAnchor.constraint(equalTo: self.historyExpensesView.leadingAnchor),
            self.historyTitleLabel.topAnchor.constraint(equalTo: self.historyExpensesView.topAnchor),
            self.historyTitleLabel.bottomAnchor.constraint(equalTo: self.historyExpensesView.bottomAnchor)
        ])
        
        //button
        self.historyExpensesView.addSubview(historyAddButton)
        historyAddButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.historyAddButton.trailingAnchor.constraint(equalTo: self.historyExpensesView.trailingAnchor),
            self.historyAddButton.bottomAnchor.constraint(equalTo: self.historyExpensesView.bottomAnchor),
            self.historyExpensesView.topAnchor.constraint(equalTo: self.historyExpensesView.topAnchor)
        ])
        
        //view
        self.view.addSubview(historyExpensesView)
        historyExpensesView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.historyExpensesView.topAnchor.constraint(equalTo: self.cardView.bottomAnchor, constant: 50),
            self.historyExpensesView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 20),
            self.historyExpensesView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -20)
        ])
    }
}

// MARK: - Configure HistoryTableView
private extension HelperViewController {
    func configureHistoryTableView() {
        self.historyTableView.backgroundColor = .systemGray6
    }
    func configureHistoryTableViewLayout() {
        self.view.addSubview(historyTableView)
        historyTableView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.historyTableView.topAnchor.constraint(equalTo: self.historyExpensesView.bottomAnchor, constant: 16),
            self.historyTableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 16),
            self.historyTableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -16),
            self.historyTableView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor)
        ])
    }
}


