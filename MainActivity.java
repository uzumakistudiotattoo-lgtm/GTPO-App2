package com.gtpo.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.graphics.Typeface;

public class MainActivity extends Activity {
    final int BLACK = Color.rgb(8,8,8);
    final int DARK = Color.rgb(17,17,17);
    final int DARK2 = Color.rgb(24,24,24);
    final int YELLOW = Color.rgb(245,196,0);
    final int WHITE = Color.WHITE;
    final int GRAY = Color.rgb(169,169,169);

    final String WHATSAPP = "5584999220508";
    final String ADDRESS = "Rua Padre Francisco Urbano Montenegro Pessoa";

    LinearLayout root;

    int dp(float n) { return (int)(n * getResources().getDisplayMetrics().density + .5f); }

    TextView text(String s, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(dp(4), dp(4), dp(4), dp(4));
        return t;
    }

    Button button(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextSize(14);
        b.setTextColor(BLACK);
        b.setAllCaps(false);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setBackgroundColor(YELLOW);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, dp(52));
        p.setMargins(0, dp(7), 0, dp(7));
        b.setLayoutParams(p);
        return b;
    }

    TextView sectionTitle(String s) {
        TextView t = text(s, 22, WHITE, true);
        t.setPadding(0, dp(26), 0, dp(8));
        return t;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BLACK);
        getWindow().setNavigationBarColor(BLACK);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(BLACK);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(10), dp(20), dp(28));
        scroll.addView(root);

        ImageView logo = new ImageView(this);
        logo.setImageResource(com.gtpo.app.R.drawable.gtpo_logo);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        logo.setAdjustViewBounds(true);
        logo.setBackgroundColor(BLACK);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(255));
        logo.setLayoutParams(lp);
        root.addView(logo);

        TextView brand = text("GRUPO DE APOIO TÁTICO, PATRIMONIAL OPERACIONAL", 12, YELLOW, true);
        brand.setGravity(Gravity.CENTER);
        root.addView(brand);

        TextView title = text("Sua segurança é nossa missão.", 30, WHITE, true);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, dp(18), 0, dp(5));
        root.addView(title);

        TextView subtitle = text("Proteção, prevenção e atendimento responsável para pessoas, empresas e patrimônios.", 15, GRAY, false);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(dp(8), 0, dp(8), dp(14));
        root.addView(subtitle);

        Button whats = button("💬 Falar com a GTPO no WhatsApp");
        whats.setOnClickListener(v -> openWhatsApp("Olá, GTPO! Gostaria de solicitar informações sobre os serviços de segurança."));
        root.addView(whats);

        Button call = button("📞 Ligar para a GTPO");
        call.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+55" + WHATSAPP));
            startActivity(i);
        });
        root.addView(call);

        root.addView(sectionTitle("Nossos serviços"));

        addService("◉", "Monitoramento", "Acompanhamento e suporte para ampliar a proteção do patrimônio.");
        addService("▣", "Segurança patrimonial", "Soluções de prevenção e controle para ambientes residenciais e empresariais.");
        addService("⌁", "Rondas e apoio", "Serviços planejados conforme a rotina e o nível de proteção necessário.");
        addService("◆", "Consultoria de segurança", "Avaliação de riscos e orientação para melhorar a segurança do local.");

        root.addView(sectionTitle("Solicite um orçamento"));

        EditText name = field("Nome completo");
        EditText phone = field("Telefone");
        EditText msg = field("Descreva o que você precisa");
        msg.setMinLines(4);
        msg.setGravity(Gravity.TOP);

        root.addView(name);
        root.addView(phone);
        root.addView(msg);

        Button send = button("Enviar solicitação pelo WhatsApp");
        send.setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            String p = phone.getText().toString().trim();
            String m = msg.getText().toString().trim();
            String body = "Olá, GTPO!%0A%0ANome: " + Uri.encode(n) +
                    "%0ATelefone: " + Uri.encode(p) +
                    "%0A%0ASolicitação: " + Uri.encode(m);
            openWhatsAppEncoded(body);
        });
        root.addView(send);

        root.addView(sectionTitle("Contato"));

        TextView contact = text("📱 WhatsApp: (84) 99922-0508\n\n📍 " + ADDRESS, 15, GRAY, false);
        contact.setPadding(dp(10), dp(8), dp(10), dp(15));
        root.addView(contact);

        Button map = button("📍 Abrir endereço no mapa");
        map.setOnClickListener(v -> {
            Uri u = Uri.parse("geo:0,0?q=" + Uri.encode(ADDRESS));
            startActivity(new Intent(Intent.ACTION_VIEW, u));
        });
        root.addView(map);

        TextView footer = text("GTPO • SEGURANÇA & MONITORAMENTO\n© " + java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + " GTPO", 12, GRAY, false);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, dp(30), 0, 0);
        root.addView(footer);

        setContentView(scroll);
    }

    EditText field(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setHintTextColor(GRAY);
        e.setTextColor(WHITE);
        e.setTextSize(15);
        e.setSingleLine(hint.length() < 20);
        e.setBackgroundColor(DARK2);
        e.setPadding(dp(14), dp(8), dp(14), dp(8));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, dp(54));
        p.setMargins(0, dp(5), 0, dp(5));
        e.setLayoutParams(p);
        return e;
    }

    void addService(String icon, String title, String desc) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(16), dp(13), dp(16), dp(13));
        box.setBackgroundColor(DARK2);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, dp(5), 0, dp(5));
        box.setLayoutParams(p);

        TextView a = text(icon + "  " + title, 18, YELLOW, true);
        TextView b = text(desc, 14, GRAY, false);
        b.setPadding(dp(4), dp(4), dp(4), 0);
        box.addView(a); box.addView(b);
        root.addView(box);
    }

    void openWhatsApp(String message) {
        String url = "https://wa.me/" + WHATSAPP + "?text=" + Uri.encode(message);
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch(Exception e) { Toast.makeText(this, "WhatsApp não encontrado.", Toast.LENGTH_LONG).show(); }
    }

    void openWhatsAppEncoded(String encoded) {
        String url = "https://wa.me/" + WHATSAPP + "?text=" + encoded;
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch(Exception e) { Toast.makeText(this, "WhatsApp não encontrado.", Toast.LENGTH_LONG).show(); }
    }
}
