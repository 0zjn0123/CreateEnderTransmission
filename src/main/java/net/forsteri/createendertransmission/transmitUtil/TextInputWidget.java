package net.forsteri.createendertransmission.transmitUtil;

import com.simibubi.create.foundation.gui.widget.AbstractSimiWidget;
import com.simibubi.create.foundation.gui.widget.Label;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.client.gui.screens.Screen;

public class TextInputWidget extends AbstractSimiWidget {

    protected Label displayLabel;

    protected String state = "";

    protected int maxLength = 16;

    protected TextInputWidget(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean charTyped(char inputtedChar, int p_94733_) {
        boolean isValid = state.length() <= maxLength;

        if(isValid){
            state = state + inputtedChar;
            onChanged();
        }

        return isValid;
    }

    public void onChanged() {
        writeToLabel();
    }

    public TextInputWidget writingTo(Label label) {
        this.displayLabel = label;
        if (label != null)
            writeToLabel();
        return this;
    }

    protected void writeToLabel() {
        displayLabel.text = Components.literal(state);
    }

    @SuppressWarnings("unused")
    public TextInputWidget withMaxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    @Override
    public boolean keyPressed(int p_94745_, int p_94746_, int p_94747_) {
        if (!(p_94745_ == 259 && state.length() > 0)) return false;

        state = this.state.substring(0, this.state.length() - 1);
        if (Screen.hasControlDown()) state = "";
        onChanged();
        return true;
    }

    public TextInputWidget setState(String state) {
        this.state = state;
        onChanged();
        return this;
    }

    public String getState() {
        return state;
    }
}
