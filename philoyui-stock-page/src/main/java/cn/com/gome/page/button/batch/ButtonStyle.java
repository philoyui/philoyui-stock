package cn.com.gome.page.button.batch;

public enum ButtonStyle {

    Blue("btn btn-secondary radius"),Green("btn btn-success radius"),Orange("btn btn-warning radius");

    private String buttonStyleClass;

    ButtonStyle(String buttonStyleClass) {
        this.buttonStyleClass = buttonStyleClass;
    }

    public String getButtonStyleClass() {
        return buttonStyleClass;
    }

}
