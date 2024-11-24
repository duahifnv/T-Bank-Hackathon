import UIKit

final class SignUpTransitionStackView: UIStackView {
    // MARK: - Properties
    let button = UIButton()
    var title = UILabel()
    
    // MARK: - Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        configure()
        
        configureTitle()
        configureTitleLayout()
        
        configureButton()
        configureButtonLayoyt()
    }
    @available(*, unavailable)
    required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Private Methods
    func configure() {
        self.axis = .vertical
        self.distribution = .fill
        self.alignment = .center
        self.spacing = 4
    }
}
// MARK: - Configure Title
private extension SignUpTransitionStackView {
    func configureTitle() {
        self.title.font = UIFont.systemFont(ofSize: 15, weight: .regular)
        self.title.textColor = .darkGray
    }
    func configureTitleLayout() {
        self.addArrangedSubview(title)
    }
}
// MARK: - Configure Button
private extension SignUpTransitionStackView {
    func configureButton() {
        button.setTitleColor(.systemYellow, for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 14, weight: .black)
    }
    func configureButtonLayoyt() {
        self.addArrangedSubview(button)
    }
}


