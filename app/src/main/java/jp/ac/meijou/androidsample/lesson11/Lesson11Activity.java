package jp.ac.meijou.androidsample.lesson11;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;
import java.util.stream.Collectors;

import jp.ac.meijou.androidsample.databinding.ActivityLesson11Binding;

/**
 * Lesson11: ネットワーク情報の取得
 */
public class Lesson11Activity extends AppCompatActivity {

    private ActivityLesson11Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLesson11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        var connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        Optional.ofNullable(connectivityManager.getNetworkCapabilities(currentNetwork))
                .map(caps -> {
                    if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return "モバイル通信";
                    } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return "WiFi";
                    } else {
                        return "その他";
                    }
                })
                .ifPresent(transport -> binding.lesson11Transport.setText(transport));

        Optional.ofNullable(connectivityManager.getLinkProperties(currentNetwork))
                .map(LinkProperties::getLinkAddresses)
                .map(linkAddresses -> linkAddresses.stream()
                        .map(LinkAddress::toString)
                        .collect(Collectors.joining("\n")))
                .ifPresent(addresses -> binding.lesson11Ipaddress.setText(addresses));
    }
}