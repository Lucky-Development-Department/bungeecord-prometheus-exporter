package org.akadia.prometheus.velocity.metrics;

import org.akadia.prometheus.interfaces.GauageMetric;
import org.akadia.prometheus.velocity.PrometheusVelocityExporter;

public class OnlinePlayer extends GauageMetric {

    public OnlinePlayer(PrometheusVelocityExporter plugin) {
        super(plugin);
    }

    @Override
    public void doCollect() {
        this.getGauge().clear();


        ((PrometheusVelocityExporter) getPlugin()).getProxyServer().getAllServers().forEach(registeredServer -> {
            String serverName = registeredServer.getServerInfo().getName();
            this.getGauge().labels(serverName, "", "").set(0);
            registeredServer.getPlayersConnected().forEach(player ->
                    this.getGauge().labels(serverName, player.getUsername(), Boolean.toString(player.isOnlineMode())).set(1));
        });
    }

    @Override
    public String getConfigKey() {
        return "online_player";
    }

    @Override
    public String getHelp() {
        return "the name of the online player in Velocity";
    }

    @Override
    public String[] getLabels() {
        return new String[]{"server", "player"};
    }
}
