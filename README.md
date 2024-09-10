

### 필요 의존성

- kubectl
- k6
- docker compose

### 첫 번째 구성 테스트

```shell
kubectl apply -f ./k8s/01/notification-systme.yml

sh ./k6/run.sh
```
