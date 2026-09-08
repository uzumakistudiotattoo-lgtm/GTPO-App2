# GTPO — gerar APK pelo celular com GitHub Codespaces

## 1. Enviar o projeto para o GitHub
- Crie um repositório no GitHub, por exemplo `GTPO-App`.
- Envie **todos os arquivos desta pasta**, mantendo as pastas `.github` e `.devcontainer`.

## 2. Abrir no Codespaces pelo celular
No repositório: **Code → Codespaces → Create codespace on main**.

Na primeira abertura, aguarde o ambiente terminar a configuração. O arquivo `.devcontainer/setup-android.sh` instala o Android SDK e Gradle automaticamente.

## 3. Gerar o APK no terminal
Execute:

```bash
gradle assembleDebug --no-daemon
```

O APK ficará em:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## 4. Jeito mais fácil: GitHub Actions
O projeto já contém `.github/workflows/build-apk.yml`.
No GitHub, abra **Actions → Build GTPO APK → Run workflow**.
Quando terminar, abra a execução e baixe o artefato **GTPO-APK**.

Essa opção é recomendada no celular porque não exige deixar o Codespace aberto durante toda a compilação.

## Dados configurados
WhatsApp: (84) 99922-0508
Endereço: Rua Padre Francisco Urbano Montenegro Pessoa

O APK é uma versão debug para testes. Para publicação na Google Play, gere uma versão assinada/AAB.
