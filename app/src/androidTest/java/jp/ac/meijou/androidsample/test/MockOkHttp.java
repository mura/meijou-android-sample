package jp.ac.meijou.androidsample.test;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * OkHttpClient のネットワーク通信をモック化するためのユーティリティクラス
 */
public class MockOkHttp {

    public interface ResponseProvider {
        Response createResponse(Request request);
    }

    /**
     * OkHttpClientの生成を傍受し、非同期リクエスト(enqueue)に対して指定したレスポンスを返すモックを作成します。
     * 戻り値の MockedConstruction は、テスト終了後に必ず close() する必要があります。
     *
     * @param responseProvider リクエストに応じてレスポンスを生成するプロバイダ
     * @return MockedConstruction インスタンス
     */
    public static MockedConstruction<OkHttpClient> mock(ResponseProvider responseProvider) {
        return Mockito.mockConstruction(OkHttpClient.class, (mock, context) -> {
            Mockito.when(mock.newCall(any(Request.class))).thenAnswer(invocation -> {
                Request request = invocation.getArgument(0);

                Call mockCall = Mockito.mock(Call.class);
                Mockito.doAnswer(callInvocation -> {
                    Callback callback = callInvocation.getArgument(0);

                    // プロバイダからレスポンスを取得し、コールバックに渡す
                    Response mockResponse = responseProvider.createResponse(request);
                    callback.onResponse(mockCall, mockResponse);

                    return null;
                }).when(mockCall).enqueue(any(Callback.class));

                return mockCall;
            });
        });
    }

    /**
     * モックレスポンス（文字列ボディ）を生成します。
     */
    public static Response createMockResponse(Request request, int code, String mediaType, String body) {
        return new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message("OK")
                .body(ResponseBody.create(body, MediaType.get(mediaType)))
                .build();
    }

    /**
     * モックレスポンス（バイト配列ボディ）を生成します。
     */
    public static Response createMockResponse(Request request, int code, String mediaType, byte[] body) {
        return new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message("OK")
                .body(ResponseBody.create(body, MediaType.get(mediaType)))
                .build();
    }
}
