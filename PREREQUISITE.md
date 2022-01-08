# Workshop prerequisite

Note: Recommended to setup these labs in your thoughtworks laptop. Also you need to have a good internet connection (mobile internet connection won’t be enough).

## PreRead

- Basic Jenkins Pipeline as a code knowledge (if completely new to pipeline as code, check this https://www.youtube.com/watch?v=s73nhwYBtzE )
- Docker Basics (if you are new to docker - please watch these ROTC recordings - https://www.youtube.com/playlist?list=PLknOipHZHwwVAM2lIKMXcy7HdEoqk0Ep1 )

## Installation

- Install Homebrew

  ```
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  ```

- Install Command Line Tools for Mac

  ```
  sudo xcode-select --install
  ```

- Install Minikube v1.24

  ```
  brew install hyperkit
  brew install minikube
  ```

- Verify Minikube and kubectl version

  ```
  ~ ❯ minikube version
  minikube version: v1.24.0
  commit: 76b94fb3c4e8ac5062daf70d60cf03ddcc0a741b
  ~ ❯ kubectl version
  Client Version: version.Info{Major:"1", Minor:"23", GitVersion:"v1.23.1", GitCommit:"86ec240af8cbd1b60bcc4c03c20da9b98005b92e", GitTreeState:"clean", BuildDate:"2021-12-16T11:33:37Z", GoVersion:"go1.17.5", Compiler:"gc", Platform:"darwin/amd64"}
  ```

- Install Helm v3.7.2
  ```
  brew install helm
  ```
- Add additional repositories for helm
  ```
  helm repo add jenkins https://charts.jenkins.io
  helm repo add evryfs-oss https://evryfs.github.io/helm-charts/
  helm repo update
  ```
- Install Java 17

  ```
  brew install openjdk@17
  ```

- Install Maven 3.8

  ```
  brew install maven
  ```

- Install Anchore Syft and Grype

  ```
  brew tap anchore/syft
  brew install syft
  brew tap anchore/grype
  brew install grype
  ```

- Install Dockle

  ```
  brew install goodwithtech/r/dockle
  Install Bridgecrew Checkov,
  brew install checkov
  ```

## Setup

- Start Minikube

  ```
  minikube start --nodes=1 --cpus=4 --memory 8192 --disk-size=35g --embed-certs=true --driver=hyperkit
  ```

  E.g,

  ```
  ~ ❯ minikube start --nodes=1 --cpus=4 --memory 8192 --disk-size=35g --embed-certs=true --driver=hyperkit
  😄 minikube v1.24.0 on Darwin 12.1
  ✨ Using the hyperkit driver based on user configuration
  👍 Starting control plane node minikube in cluster minikube
  🔥 Creating hyperkit VM (CPUs=4, Memory=8192MB, Disk=35840MB) ...
  🐳 Preparing Kubernetes v1.22.3 on Docker 20.10.8 ...
  ▪ Generating certificates and keys ...
  ▪ Booting up control plane ...
  ▪ Configuring RBAC rules ...
  🔎 Verifying Kubernetes components...
  ▪ Using image gcr.io/k8s-minikube/storage-provisioner:v5
  🌟 Enabled addons: storage-provisioner, default-storageclass
  🏄 Done! kubectl is now configured to use "minikube" cluster and "default" namespace by default
  ~ ❯
  ```

- Check minikube installation

  ```
  kubectl get pods --all-namespaces
  ```

  E.g,

  ```
  ~ ❯ kubectl get pods --all-namespaces
  NAMESPACE NAME READY STATUS RESTARTS AGE
  kube-system coredns-78fcd69978-zqqk7 1/1 Running 0 43s
  kube-system etcd-minikube 1/1 Running 0 56s
  kube-system kube-apiserver-minikube 1/1 Running 0 56s
  kube-system kube-controller-manager-minikube 1/1 Running 0 56s
  kube-system kube-proxy-gk2rt 1/1 Running 0 43s
  kube-system kube-scheduler-minikube 1/1 Running 0 56s
  kube-system storage-provisioner 1/1 Running 0 55s
  ~ ❯
  ```

- Create a file with name “jenkins-values.yaml” and below contents

  ```
  # Custom values for jenkins.
  controller:
  # List of plugins to be installed during Jenkins controller start
  installPlugins:
    - kubernetes:1.30.1
    - kubernetes-client-api:5.4.1
    - workflow-aggregator:2.6
    - git:4.10.1
    - configuration-as-code:1.55
    - blueocean:latest
    - dependency-check-jenkins-plugin:latest
    - dependency-track:latest
    - warnings-ng:latest
  ```

- Install Jenkins

  ```
    helm install jenkins jenkins/jenkins -f jenkins-values.yaml
  ```

- Wait for the jenkins pod to start

  ```
  kubectl get pods --all-namespaces
  ```

- Get admin user password of Jenkins

  ```
  kubectl exec --namespace default -it svc/jenkins -c jenkins -- /bin/cat /run/secrets/chart-admin-password && echo
  ```

  Note: Make a note of the password

- Forward Jenkins server port to access from local machine
  kubectl --namespace default port-forward svc/jenkins 8080:8080
- open http://localhost:8080
- Login with username “admin” and the password from the above step.

### [Optional]

- Pull the below docker images to avoid pulling images during workshop
  ```
  > minikube ssh
  > $ docker pull gradle:7-jdk17-alpine
  > $ docker pull openjdk:17.0.1
  > $ docker pull rmkanda/docker-tools:latest
  > $ docker pull licensefinder/license_finder
  > $ docker pull rmkanda/trufflehog
  ```
