package jp.ac.meijou.androidsample.test;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * テスト用のダミーBitmapを生成するユーティリティクラス
 */
public class MockBitmap {

    /**
     * 1x1のダミーBitmapを生成し、PNGエンコードしたバイト配列を返します。
     * ネットワークモックのレスポンスボディなどに使用します。
     *
     * @return PNG画像のバイト配列
     */
    public static byte[] createBitmapByteArray() {
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }
}
