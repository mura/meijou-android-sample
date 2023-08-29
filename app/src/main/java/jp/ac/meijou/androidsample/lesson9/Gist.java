package jp.ac.meijou.androidsample.lesson9;

import java.util.Map;

/**
 * GistのJSONレスポンス
 */
public class Gist {
    public Map<String, GistFile> files;

    public static class GistFile {
        public String content;
    }
}
