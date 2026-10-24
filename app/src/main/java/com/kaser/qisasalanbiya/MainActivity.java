package com.kaser.qisasalanbiya;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.*;

public class MainActivity extends Activity {

    LinearLayout main;
    ScrollView scroll;

    String[] prophets = {
        "آدم","إدريس","نوح","هود","صالح",
        "إبراهيم","لوط","إسماعيل","إسحاق","يعقوب",
        "يوسف","أيوب","شعيب","موسى","هارون",
        "ذو الكفل","داود","سليمان","إلياس","اليسع",
        "يونس","زكريا","يحيى","عيسى","محمد"
    };

    int gold = Color.rgb(255,205,75);
    int white = Color.WHITE;
    int dark = Color.rgb(5,35,31);

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.rgb(4,30,27));
        getWindow().setNavigationBarColor(Color.rgb(4,30,27));
        welcome();
    }

    GradientDrawable shape(int c1, int c2, int radius) {
        GradientDrawable d = new GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            new int[]{c1,c2}
        );
        d.setCornerRadius(radius);
        d.setStroke(2, gold);
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        t.setPadding(12,18,12,18);
        return t;
    }

    void welcome() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        box.setPadding(24,35,24,35);
        box.setBackground(shape(
            Color.rgb(3,45,38),
            Color.rgb(12,105,75),45
        ));

        TextView symbol = text("☪",75,gold);
        box.addView(symbol);

        TextView title = text(
            "أهلاً بك في برنامج\nالكاسر لقصص الأنبياء",
            30,white
        );
        box.addView(title);

        TextView subtitle = text(
            "رحلة إيمانية بين قصص الأنبياء والعبر",
            18,gold
        );
        box.addView(subtitle);

        TextView label = text(
            "نور المعرفة • عظمة القصص • دروس الحياة",
            15,white
        );
        box.addView(label);

        Button start = new Button(this);
        start.setText("ابدأ الرحلة الآن  ✦");
        start.setTextSize(20);
        start.setTextColor(dark);
        start.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(gold)
        );
        box.addView(start);

        start.setOnClickListener(v -> home());

        AlphaAnimation fade = new AlphaAnimation(0f,1f);
        fade.setDuration(1400);
        box.startAnimation(fade);

        ScaleAnimation scale = new ScaleAnimation(
            0.85f,1f,0.85f,1f,
            ScaleAnimation.RELATIVE_TO_SELF,0.5f,
            ScaleAnimation.RELATIVE_TO_SELF,0.5f
        );
        scale.setDuration(1000);
        box.startAnimation(scale);

        setContentView(box);
    }

    void home() {
        scroll = new ScrollView(this);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(16,20,16,20);
        main.setBackgroundColor(Color.rgb(5,35,31));

        main.addView(text("☪ الكاسر لقصص الأنبياء",27,gold));
        main.addView(text(
            "قصص وعبر من كتاب الله الكريم",
            17,white
        ));

        for (int i=0;i<prophets.length;i++) {
            final int index=i;

            Button btn = new Button(this);
            btn.setText("✦  قصة " + prophets[i] + " عليه السلام");
            btn.setTextSize(18);
            btn.setTextColor(white);
            btn.setAllCaps(false);
            btn.setBackground(shape(
                Color.rgb(8,75,57),
                Color.rgb(5,45,39),25
            ));

            LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                    -1,-2
                );
            lp.setMargins(0,7,0,7);
            main.addView(btn,lp);

            btn.setOnClickListener(v -> story(index));
        }

        scroll.addView(main);
        setContentView(scroll);
    }

    void story(int index) {
        scroll = new ScrollView(this);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(20,25,20,25);
        page.setBackgroundColor(Color.rgb(5,35,31));

        page.addView(text(
            "قصة " + prophets[index] + " عليه السلام",
            27,gold
        ));

        TextView body = text(
            "أضف النص الكامل الموثق لقصة " +
            prophets[index] +
            " عليه السلام في ملف المحتوى الخاص بالتطبيق.",
            20,white
        );
        body.setGravity(Gravity.RIGHT);
        page.addView(body);

        Button back = new Button(this);
        back.setText("العودة إلى قائمة القصص");
        back.setOnClickListener(v -> home());
        page.addView(back);

        scroll.addView(page);
        setContentView(scroll);
    }
}