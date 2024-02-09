[![Release](https://github.com/weihao/bungeecord-prometheus-exporter/actions/workflows/release.yml/badge.svg)](https://github.com/weihao/bungeecord-prometheus-exporter/actions/workflows/release.yml)
![GitHub all releases](https://img.shields.io/github/downloads/weihao/bungeecord-prometheus-exporter/total)
<a href="https://www.buymeacoffee.com/nokb"><img src="https://img.buymeacoffee.com/button-api/?text=Buy Me a Coffee&emoji=☕&slug=nokb&button_colour=f07167&font_colour=000000&font_family=Arial&outline_colour=000000&coffee_colour=FFDD00" height="20px" /></a>

# BungeeCord Prometheus Exporter

A **plugin** that exports network stats for Prometheus.

> If you don't run a network proxy, you might also be interested
> in [Prometheus Exporter](https://github.com/sladkoff/minecraft-prometheus-exporter) for a `single server` metrics!

## Why BungeeCord Prometheus Exporter?

- monitor your server infrastructure
- track your players, events, and servers
    - player pings histogram
    - online player list
    - server list ping, connects, disconnects, kicks, and chat event counters
- automates the collection, management and viewing of your data
- get alerts for service outages

## Runtime Requirement

- Java 11+

## Compatible Proxy

- [x] Velocity
- [x] BungeeCord / Waterfall
- [x] RedisBungee

## Quick Start

Drop the bungeecord-prometheus-exporter.jar into your plugins directory and start your proxy server.

After startup, the Prometheus metrics endpoint should be available at ``localhost:9985/metrics`` (assuming localhost is
the server hostname).

If running inside the docker, change the host to `0.0.0.0` to allow Prometheus and other services to reach the endpoint.

The metrics port can be customized in the plugin's config.json (a default config will be created after the first use).

## Prometheus config

Add the following job to the ``scrape_configs`` section of your Prometheus configuration `prometheus.yml`:

### Single Proxy

```yml
  - job_name: 'bungeecord'
    scrape_interval: 5s

    static_configs:
      - targets: [ 'localhost:9985' ]
        labels:
          proxy_name: 'proxy'
```

### Multiple proxies

You can use labels in your Prometheus scrape configuration to distinguish between multiple proxies:

```yml
  - job_name: 'bungeecord'
    scrape_interval: 5s

    static_configs:
      - targets: [ 'localhost:9985' ]
        labels:
          proxy_name: 'proxy1'
      - targets: [ 'localhost:9226' ]
        labels:
          proxy_name: 'proxy2'
```

## Import Grafana Dashboard

1. Navigate to Grafana -> Dashboards -> Import
1. Paste in or upload [default dashboard](https://github.com/weihao/bungeecord-prometheus-exporter/tree/main/dashboards)
1. ![default dashboard](https://raw.githubusercontent.com/weihao/bungeecord-prometheus-exporter/main/images/dashboard.png)

## Notes

RedisBungee is supported
but [disabled by default](https://github.com/weihao/bungeecord-prometheus-exporter/blob/main/src/main/resources/config.json)
.
RedisBungee metrics are not used in the dashboard because we are
already collecting metrics from single instances. However, if you still want to integrate with RedisBungee, free feel to
enable it and modify the dashboard.

## Links

This project is indexed at:

- [exporters](https://github.com/prometheus/docs/blob/main/content/docs/instrumenting/exporters.md#miscellaneous)
- [ports](https://github.com/prometheus/prometheus/wiki/Default-port-allocations#exporters-starting-at-9100)
