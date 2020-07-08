<p align="center">
 <img src="https://img.shields.io/badge/Cjlgb%20Design%20Security-1.0.0-brightgreen" alt="Build Status">
 <img src="https://img.shields.io/badge/Ant%20Design%20Vue-1.5.6-brightgreen" alt="Build Status">
 <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxto.SR5-blue.svg" alt="Coverage Status">
 <img src="https://img.shields.io/badge/Spring%20Boot-2.3.1.RELEASE-blue.svg" alt="Downloads">
</p>

- 基于 Spring Cloud Hoxton 、Spring Boot 2.2、 OAuth2 的RBAC权限管理系统  
- 基于数据驱动视图的理念封装 Ant Design Vue，即使没有 vue 的使用经验也能快速上手
- 提供 lambda 、stream api 、webflux 的生产实践   


#### <a target="_blank" href="https://cjlgb-design-oauth.cdn.bcebos.com/video%2F20200703003537.mp4">预览</a>

- 在线预览：http://open.cjlgb.com:8888/
- 开发者平台代码：https://gitee.com/cjlgb/cjlgb-design-developer
- 后端代码：https://gitee.com/cjlgb/cjlgb-cloud-platform


#### 核心依赖

依赖 | 版本
---|---
Spring Boot |  2.3.1.RELEASE
Spring Cloud | Hoxton.SR5
Mybatis Plus | 3.3.2
Ant Design Vue | 1.5.6
   
#### 模块说明
```
cjlgb-cloud-platform
├── cjlgb-design-common
     ├── cjlgb-design-common-core -- 公共工具类核心包
     ├── cjlgb-design-common-mybatis -- 整合MybatisPlus
     ├── cjlgb-design-common-oauth -- Oauth认证模块接口
     ├── cjlgb-design-common-security -- 分布式权限框架
     ├── cjlgb-design-common-system -- 系统服务模块接口
├── cjlgb-design-gateway -- API网关[10001]
└── cjlgb-design-developer -- 开发者服务模块[0]
└── cjlgb-design-oauth -- Oauth服务模块[0]
└── cjlgb-design-system -- 系统服务模块[0]
	 
```

#### Hosts配置(Docker环境下可忽略)
~~~
127.0.0.1	cjlgb-design-gateway
127.0.0.1	cjlgb-design-nacos
127.0.0.1	cjlgb-design-redis
127.0.0.1	cjlgb-design-mysql
~~~

#### 单机版 Nacos Docker-Commpose 脚本
~~~
version: "3"
networks:
  default:
    external:
      name: cjlgb-cloud-platform
services:
  cjlgb-design-nacos:
    image: nacos/nacos-server:1.1.4
    container_name: cjlgb-design-nacos
    environment:
      - PREFER_HOST_MODE=hostname
      - MODE=standalone
    restart: on-failure
~~~

#### 单机版 Redis Docker-Commpose 脚本
~~~
version: '3'
networks:
  default:
    external:
      name: cjlgb-cloud-platform
services:
  cjlgb-design-redis:
    restart: always
    image: redis
    hostname: cjlgb-design-redis
    container_name: cjlgb-design-redis
~~~

#### Nginx Docker-Commpose 脚本
~~~
version: '3'
networks:
  default:
    external:
      name: cjlgb-cloud-platform
services:
  cjlgb-design-nginx:
    container_name: cjlgb-design-nginx
    image: nginx
    volumes:
      - /opt/apps/docker-container/cjlgb-design-nginx/conf/nginx.conf:/etc/nginx/nginx.conf
      - /opt/apps/docker-container/cjlgb-design-nginx/website:/usr/share/nginx/html
    ports:
      - 80:80
~~~

#### Nginx 配置文件
~~~
worker_processes  1;
events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
    access_log off;
    gzip  on;

    server {
        listen       80;
        server_name  nacos.cjlgb.com;
        location / {
            proxy_pass    http://cjlgb-design-nacos:8848/nacos/;
        }
    }

    server {
        listen       80;
        server_name  admin.cjlgb.com;
        
        location /apis/ {
            proxy_pass    http://cjlgb-design-gateway:10001/;
        }
        
        location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
        }
    }
}
~~~




