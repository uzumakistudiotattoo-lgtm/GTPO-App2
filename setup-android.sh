#!/usr/bin/env bash
set -e
SDK_ROOT="$HOME/android-sdk"
mkdir -p "$SDK_ROOT/cmdline-tools"
if [ ! -x "$SDK_ROOT/cmdline-tools/latest/bin/sdkmanager" ]; then
  cd /tmp
  wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O cmdline-tools.zip
  rm -rf "$SDK_ROOT/cmdline-tools/latest" /tmp/cmdline-tools
  mkdir -p /tmp/cmdline-tools
  unzip -q cmdline-tools.zip -d /tmp/cmdline-tools
  mv /tmp/cmdline-tools/cmdline-tools "$SDK_ROOT/cmdline-tools/latest"
fi
export ANDROID_HOME="$SDK_ROOT"
export ANDROID_SDK_ROOT="$SDK_ROOT"
export PATH="$SDK_ROOT/cmdline-tools/latest/bin:$SDK_ROOT/platform-tools:$PATH"
echo 'export ANDROID_HOME="$HOME/android-sdk"' >> "$HOME/.bashrc"
echo 'export ANDROID_SDK_ROOT="$HOME/android-sdk"' >> "$HOME/.bashrc"
echo 'export PATH="$HOME/android-sdk/cmdline-tools/latest/bin:$HOME/android-sdk/platform-tools:$PATH"' >> "$HOME/.bashrc"
yes | sdkmanager --licenses >/dev/null || true
sdkmanager "platform-tools" "platforms;android-35" "build-tools;35.0.0"
if ! command -v gradle >/dev/null 2>&1; then
  cd /tmp
  wget -q https://services.gradle.org/distributions/gradle-8.7-bin.zip -O gradle.zip
  sudo mkdir -p /opt/gradle
  sudo unzip -q gradle.zip -d /opt/gradle
  echo 'export PATH="/opt/gradle/gradle-8.7/bin:$PATH"' >> "$HOME/.bashrc"
fi
echo 'Android/Gradle environment ready.'
