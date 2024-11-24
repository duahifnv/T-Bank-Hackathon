import UIKit

// MARK: - LoginMainButton
final class LoginMainButton: UIButton {
    
    // MARK: - Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        configure()
    }
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }
    
    // MARK: - Private Methods
    private func configure() {
        self.layer.cornerRadius = 10
        self.backgroundColor = .systemYellow
        self.titleLabel?.font = UIFont.systemFont(ofSize: 23, weight: .medium)
    }
}


