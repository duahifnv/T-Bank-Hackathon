import UIKit

final class LoginTextField: UITextField {
    // MARK: - Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        configure()
    }
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Override Methods
    override func placeholderRect(forBounds bounds: CGRect) -> CGRect {
        return CGRectInset(bounds, 10, 0)
    }
    override func textRect(forBounds bounds: CGRect) -> CGRect {
        return CGRectInset(bounds, 10, 0)
    }
    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        return CGRectInset(bounds, 10, 0)
    }
    
    // MARK: - Private Methods
    private func configure() {
        self.backgroundColor = .white
        self.layer.borderWidth = 1
        self.layer.borderColor = UIColor.systemGray5.cgColor
        self.layer.cornerRadius = 10
        
        self.attributedPlaceholder = NSAttributedString(string: self.placeholder ?? "", attributes: [
            .font: UIFont.systemFont(ofSize: 12, weight: .light),
            .foregroundColor: UIColor.systemGray4
            ])
    }
}


