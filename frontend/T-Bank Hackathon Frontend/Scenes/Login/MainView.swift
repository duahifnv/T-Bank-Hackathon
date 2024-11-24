import UIKit
// MARK: - MainView
final class MainView: UIView {
    // MARK: - GUI
    private let logo = UIImageView()
    let mainView = UIView()
    let mainTitle = UILabel()
    let secondTitle = UILabel()
    
    
    // MARK: - LifeCycle
    override func layoutSubviews() {
        super.layoutSubviews()
        
        let blurVisualEffectView = UIVisualEffectView(effect: UIBlurEffect(style: .extraLight))
        blurVisualEffectView.frame = self.bounds
        blurVisualEffectView.alpha = 0.7
        blurVisualEffectView.layer.cornerRadius = self.mainView.layer.cornerRadius
        blurVisualEffectView.clipsToBounds = true
        self.insertSubview(blurVisualEffectView, at: 0)
        
    }
    
    // MARK: - Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        configureMainView()
        configureMainViewLayout()
        
        configureLogo()
        configureLogoLayout()
        
        configureMainTitle()
        configureMainTitleLayout()
        
        configureSecondTitle()
        confiureSecondTitleLayout()
        
    }
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Private Methods
    private func configureMainView() {
        mainView.backgroundColor = .white
        mainView.alpha = 0.1
        mainView.layer.cornerRadius = 20
        mainView.clipsToBounds = true
    }
    private func configureMainViewLayout() {
        self.addSubview(mainView)
        
        mainView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            mainView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            mainView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            mainView.topAnchor.constraint(equalTo: self.topAnchor),
            mainView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
    }
}

// MARK: - Configure Logo
private extension MainView {
    func configureLogo() {
        self.logo.image = UIImage.logo
        self.logo.contentMode = .scaleAspectFit
        self.logo.layer.cornerRadius = 10
        self.logo.clipsToBounds = true
    }
    func configureLogoLayout() {
        self.addSubview(logo)
        
        logo.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.logo.centerXAnchor.constraint(equalTo: self.centerXAnchor),
            self.logo.topAnchor.constraint(equalTo: self.topAnchor, constant: 24),
            self.logo.widthAnchor.constraint(equalToConstant: 50),
            self.logo.heightAnchor.constraint(equalToConstant: 50)
        ])
    }
}

// MARK: - Configure MainTitle
private extension MainView {
    func configureMainTitle() {
        self.mainTitle.font = UIFont.systemFont(ofSize: 30, weight: .bold)
    }
    func configureMainTitleLayout() {
        self.addSubview(mainTitle)
        mainTitle.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.mainTitle.topAnchor.constraint(equalTo: self.logo.bottomAnchor, constant: 20),
            self.mainTitle.centerXAnchor.constraint(equalTo: self.centerXAnchor)
        ])
    }
}

// MARK: - Configure SecondTitle
private extension MainView {
    func configureSecondTitle() {
        self.secondTitle.font = UIFont.systemFont(ofSize: 15, weight: .regular)
        self.secondTitle.textColor = .darkGray
        self.secondTitle.numberOfLines = 0
    }
    func confiureSecondTitleLayout() {
        self.addSubview(secondTitle)
        secondTitle.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.secondTitle.topAnchor.constraint(equalTo: self.mainTitle.bottomAnchor, constant: 16),
            self.secondTitle.centerXAnchor.constraint(equalTo: self.centerXAnchor)
        ])
    }
}




