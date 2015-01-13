package channelsoft.com.topbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义topbar组件
 * Created by yuanshun on 2015/1/13.
 */
public class Topbar extends RelativeLayout {

    //组件应该由两个按钮与一个文字描述组成
    private Button leftButton,rightButton;
    private TextView tvTitle;




    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
