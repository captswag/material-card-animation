package anjithsasindran.materialcardanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    ImageView imageView;
    ImageButton imageButton, closeButton;
    RelativeLayout revealView;
    LinearLayout layoutButtons;
    Animation alphaAppear, alphaDisappear;
    int x, y, width, height, hypotenuse;
    float pixelDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pixelDensity = getResources().getDisplayMetrics().density;

        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation);
        closeButton = (ImageButton) findViewById(R.id.closeButton);
        revealView = (RelativeLayout) findViewById(R.id.linearView);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);

        alphaAppear = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        alphaDisappear = AnimationUtils.loadAnimation(this, R.anim.alpha_disappear);
    }

    public void launchTwitter(View view) {

        /*
         MARGIN_RIGHT = 16dp
         FAB_BUTTON_RADIUS = 28dp
         */
        width = imageView.getWidth();
        height = imageView.getHeight();

        x = width / 2;
        y = height / 2;
        hypotenuse = (int) Math.hypot(x, y);

        x = (int) (x - ((16 * pixelDensity) + (28 * pixelDensity)));

        FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                revealView.getLayoutParams();
        parameters.height = imageView.getHeight();
        revealView.setLayoutParams(parameters);

        imageButton.animate()
            .translationX(-x)
            .translationY(-y)
            .setDuration(200)
            .setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                Animator anim = ViewAnimationUtils.createCircularReveal(revealView, width / 2, height / 2, 28 * pixelDensity, hypotenuse);
                anim.setDuration(350);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        layoutButtons.setVisibility(View.VISIBLE);
                        closeButton.setVisibility(View.VISIBLE);
                        layoutButtons.startAnimation(alphaAppear);
                        closeButton.startAnimation(alphaAppear);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                imageButton.setVisibility(View.GONE);
                revealView.setVisibility(View.VISIBLE);
                anim.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void closeTwitter(View view) {

        alphaDisappear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animator anim = ViewAnimationUtils.createCircularReveal(revealView, width / 2, height / 2, hypotenuse, 28 * pixelDensity);
                anim.setDuration(350);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        revealView.setVisibility(View.GONE);
                        imageButton.setVisibility(View.VISIBLE);

                        ObjectAnimator objectAnimatorX
                                = ObjectAnimator.ofFloat(imageButton, "translationX", -x, 0f);
                        ObjectAnimator objectAnimatorY
                                = ObjectAnimator.ofFloat(imageButton, "translationY", -y, 0f);

                        objectAnimatorX.setDuration(200);
                        objectAnimatorY.setDuration(200);

                        objectAnimatorX.start();
                        objectAnimatorY.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                anim.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        layoutButtons.setVisibility(View.GONE);
        closeButton.setVisibility(View.GONE);
        layoutButtons.startAnimation(alphaDisappear);
        closeButton.startAnimation(alphaDisappear);
    }
}