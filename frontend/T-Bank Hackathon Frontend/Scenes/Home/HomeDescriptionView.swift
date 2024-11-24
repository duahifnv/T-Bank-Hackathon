import UIKit

// MARK: - HomeDescriptionView
final class HomeDescriptionView: UIView {
    // MARK: - Properties
    private let title: String
    private let text: String
    private let color: UIColor
    
    // MARK: - GUI
    private let titleLabel = UILabel()
    private let textLabel = UILabel()
    
    // MARK: - Init
    init(title: String, text: String, color: UIColor) {
        self.title = title
        self.text = text
        self.color = color
        
        super.init(frame: .zero)
        
        configure()
        
        configureTitleLabel()
        configureTitleLabelLayout()
        
        configureTextLabel()
        configureTextLabelLayout()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    // MARK: - Private Methods
    private func configure() {
        self.layer.cornerRadius = 20
        self.backgroundColor = color
    }
}

// MARK: - Configure TitleLabel
private extension HomeDescriptionView {
    func configureTitleLabel() {
        self.titleLabel.text = title
        self.titleLabel.font = UIFont.systemFont(ofSize: 24, weight: .bold)
    }
    func configureTitleLabelLayout() {
        self.addSubview(titleLabel)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.titleLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 8),
            self.titleLabel.centerXAnchor.constraint(equalTo: self.centerXAnchor)
        ])
    }
}

// MARK: - Configure TextLabel
private extension HomeDescriptionView {
    func configureTextLabel() {
        self.textLabel.text = text
        self.textLabel.numberOfLines = 0
        self.textLabel.textAlignment = .center
    }
    func configureTextLabelLayout() {
        self.addSubview(textLabel)
        textLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.textLabel.topAnchor.constraint(equalTo: self.titleLabel.bottomAnchor, constant: 8),
            self.textLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            self.textLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            self.textLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -16)
        ])
    }
}


