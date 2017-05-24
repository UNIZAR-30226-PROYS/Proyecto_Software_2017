package barbarahliskov.cambialibros;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


public class ButtonList extends android.support.v7.widget.AppCompatButton{

    public ButtonList(Context context)
    {
        super(context);
    }

    public ButtonList(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ButtonList(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed)
    {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed())
        {
            return;
        }
        super.setPressed(pressed);
    }
}