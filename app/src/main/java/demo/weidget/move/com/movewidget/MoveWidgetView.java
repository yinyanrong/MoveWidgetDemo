package demo.weidget.move.com.movewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by My on 2016/12/8.
 */
public class MoveWidgetView extends LinearLayout {

    private int mStartX;
    private int mStartY;
    private int mParentWidth;
    private int mParentHeight;
    private int mWidth;
    private int mHight;

    public MoveWidgetView(Context context) {
        this(context, null);
    }

    public MoveWidgetView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量控件的宽高
        mWidth = getWidth();
        mHight = getHeight();
        //测量父类的宽高
        ViewGroup   group=(ViewGroup)getParent();
        mParentWidth=group.getWidth();
        mParentHeight=group.getHeight();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int moveX = (int) event.getX();
        int moveY = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = moveX;
                mStartY = moveY;

                break;
            case MotionEvent.ACTION_MOVE:
                //每次滑动的 距离
                int xdiff = moveX - mStartX;
                int ydiff = moveY - mStartY;

                int vLeft = getLeft() + xdiff;
                int vRight = 0;
                if(vLeft<=0){
                     vLeft=0;
                    vRight= vLeft + mWidth;
                }else{
                    vRight=getRight()+xdiff;
                }
                int vTop = getTop() + ydiff;
                int vBotton = 0;
                if(vTop<=0){
                    vTop=0;
                    vBotton = vTop + mHight;
                }else{
                    vBotton = getBottom() + ydiff;
                }
                if(vRight>mParentWidth){
                    vRight=mParentWidth;
                    vLeft=mParentWidth-mWidth;
                }
                if(vBotton>mParentHeight){
                    vBotton=mParentHeight;
                    vTop=mParentHeight-mHight;
                }
                layout(vLeft, vTop, vRight, vBotton);
                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return true;
    }
}
