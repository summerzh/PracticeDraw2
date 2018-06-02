package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice05ComposeShaderView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice05ComposeShaderView(Context context) {
        super(context);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader1);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        BitmapShader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // shaderA是dst, shaderB是res,也就是第一个shader是目标shader,第二个是源shader
        // src_over的效果就是把src覆盖在dst上
        ComposeShader composeShader = new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_IN);
        paint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(200, 200, 200, paint);
    }
}
