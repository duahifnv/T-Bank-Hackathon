import UIKit

final class AddViewController: UIViewController {
    // MARK: - Properties
    var viewModel: AddViewModel
    
    // MARK: - GUI
    let titleLabel = UILabel()
    
    let stackView = UIStackView()
    
    let typeTextField = LoginTextField()
    let categoryTextField = LoginTextField()
    let dateTextField = LoginTextField()
    let sumTextField = LoginTextField()
    
    let typePickerView = UIPickerView()
    let categoryPickerView = UIPickerView()
    let datePickerView = UIDatePicker()
    
    let addButton = LoginMainButton()
    
    init(viewModel: AddViewModel) {
        self.viewModel = viewModel
        
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = .white
        
        setupPickerViews()
        
        configureTitleLabel()
        configureTitleLabelLayout()
        
        configureStackView()
        configureStackViewLayout()
        
        configureTypeTextField()
        configureTypeTextFieldLayout()
        
        configureCategoryTextField()
        configureCategoryTextFieldLayout()
        
        configureDateTextField()
        configureDateTextFieldLayout()
        
        configureSumTextField()
        configureSumTextFieldLayout()
        
        
        configureAddButton()
        configureAddButtonLayout()
        
        print(viewModel)
    }
    func setupPickerViews() {
        self.typePickerView.delegate = self
        self.typePickerView.dataSource = self
        
        self.categoryPickerView.delegate = self
        self.categoryPickerView.dataSource = self
        
        self.datePickerView.datePickerMode = .date
        self.datePickerView.preferredDatePickerStyle = .wheels
        self.datePickerView.addTarget(self, action: #selector(datePickerAction(datePicker:)), for: .valueChanged)
        
    }
    
    func removeTime(dateWithTime: Date) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd-MM-yyyy"
        let dateWithoutTimeString = dateFormatter.string(from: dateWithTime)
        
        return dateWithoutTimeString
    }
}

// MARK: - Configure TitleLabel
private extension AddViewController {
    func configureTitleLabel() {
        self.titleLabel.font = UIFont.systemFont(ofSize: 25, weight: .bold)
        self.titleLabel.text = "Новая операция"
    }
    func configureTitleLabelLayout() {
        self.view.addSubview(titleLabel)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.titleLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.titleLabel.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 20)
        ])
    }
}

// MARK: - Configure StackView
private extension AddViewController {
    func configureStackView() {
        self.stackView.axis = .vertical
        self.stackView.spacing = 30
    }
    func configureStackViewLayout() {
        self.view.addSubview(stackView)
        stackView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.stackView.topAnchor.constraint(equalTo: self.titleLabel.bottomAnchor, constant: 40),
            self.stackView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor, constant: 40),
            self.stackView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor, constant: -40)
        ])
    }
}

// MARK: - Configure TypeTextField
private extension AddViewController {
    func configureTypeTextField() {
        self.typeTextField.placeholder = "Доход / Расход"
        self.typeTextField.inputView = typePickerView
        self.typeTextField.delegate = self
    }
    func configureTypeTextFieldLayout() {
        self.stackView.addArrangedSubview(typeTextField)
        typeTextField.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            typeTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 20)
        ])
    }
}

// MARK: - Configure CategoryTextField
private extension AddViewController {
    func configureCategoryTextField() {
        self.categoryTextField.placeholder = "Категория"
        self.categoryTextField.inputView = categoryPickerView
        self.categoryTextField.delegate = self
    }
    func configureCategoryTextFieldLayout() {
        self.stackView.addArrangedSubview(categoryTextField)
        categoryTextField.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            categoryTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 20)
        ])
    }
}

// MARK: - Configure DateTextField
private extension AddViewController {
    func configureDateTextField() {
        self.dateTextField.placeholder = "Дата"
        self.dateTextField.inputView = datePickerView
        self.dateTextField.delegate = self
    }
    func configureDateTextFieldLayout() {
        self.stackView.addArrangedSubview(dateTextField)
        dateTextField.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            dateTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 20)
        ])
    }
}

// MARK: - Configure SumTextField
private extension AddViewController {
    func configureSumTextField() {
        self.sumTextField.placeholder = "Сумма"
        self.sumTextField.keyboardType = .decimalPad
        self.sumTextField.delegate = self
    }
    func configureSumTextFieldLayout() {
        self.stackView.addArrangedSubview(sumTextField)
        sumTextField.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            sumTextField.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 1 / 20)
        ])
    }
}

// MARK: - Configure AddButton
private extension AddViewController {
    func configureAddButton() {
        self.addButton.setTitle("Добавить", for: .normal)
    }
    func configureAddButtonLayout() {
        self.view.addSubview(addButton)
        addButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            self.addButton.topAnchor.constraint(equalTo: self.stackView.bottomAnchor, constant: 40),
            self.addButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
            self.addButton.widthAnchor.constraint(equalTo: self.view.widthAnchor, multiplier: 0.7)
        ])
    }
    func getAllValues() -> (String, String, String, Double)? {
        let sum = self.sumTextField.text!
        let updatedSum = sum.replacingOccurrences(of: ",", with: ".")
        
        if let doubleValue = Double(updatedSum) {
            return (self.typeTextField.text!, self.categoryTextField.text!, self.dateTextField.text!, doubleValue)
        } else {
            return nil
        }
    }
}

// MARK: - UIPickerViewDataSource
extension AddViewController: UIPickerViewDataSource {
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        
        switch pickerView {
        case typePickerView:
            return viewModel.typeValues.count
        case categoryPickerView:
            print(viewModel.categoryValues.count)
            return viewModel.categoryValues.count
            
        default:
            return 0
        }
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch pickerView {
        case typePickerView:
            return viewModel.typeValues[row].rawValue
        case categoryPickerView:
            return viewModel.categoryValues[row].rawValue
            
        default:
            return nil
        }
    }
    
}
// MARK: - UIPickerViewDelegate
extension AddViewController: UIPickerViewDelegate {
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        switch pickerView {
        case typePickerView:
            self.typeTextField.text = self.viewModel.typeValues[row].rawValue
        default:
            self.categoryTextField.text = self.viewModel.categoryValues[row].rawValue
        }
    }
}

// MARK: - Configure DatePicker
private extension AddViewController {
    @objc func datePickerAction(datePicker: UIDatePicker) {
        self.dateTextField.text = self.removeTime(dateWithTime: datePicker.date)
    }
}

// MARK: - UITextFieldDelegate
extension AddViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        switch textField {
        case typeTextField:
            categoryTextField.becomeFirstResponder()
        case categoryTextField:
            dateTextField.becomeFirstResponder()
        case dateTextField:
            sumTextField.becomeFirstResponder()
        default:
            self.view.endEditing(true)
        }
    }
}


