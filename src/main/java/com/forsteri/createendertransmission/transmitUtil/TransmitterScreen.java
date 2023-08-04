package com.forsteri.createendertransmission.transmitUtil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.gui.AbstractSimiScreen;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import com.simibubi.create.foundation.gui.widget.IconButton;
import com.simibubi.create.foundation.gui.widget.Label;
import com.simibubi.create.foundation.gui.widget.ScrollInput;
import com.simibubi.create.foundation.gui.widget.SelectionScrollInput;
import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Lang;
import com.forsteri.createendertransmission.entry.TransmissionPackets;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TransmitterScreen<T extends KineticBlockEntity & ITransmitter> extends AbstractSimiScreen {

    public T te;

    public ItemStack renderedItem;

    public TransmitterScreen(T te, ItemStack renderedItem) {
        super(Lang.translateDirect("gui.sequenced_gearshift.title"));
        this.te = te;
        this.renderedItem = renderedItem;
    }

    private final AllGuiTextures background = AllGuiTextures.WAND_OF_SYMMETRY;

    private ScrollInput areaChannel;

    private EditBox areaTestInput;

    @Override
    protected void renderWindow(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        int x = guiLeft;
        int y = guiTop;

        background.render(ms, x, y, this);
        font.draw(ms, renderedItem.getHoverName(), x + 11, y + 4, 0x6B3802);

        GuiGameElement.of(renderedItem)
                .<GuiGameElement.GuiRenderBuilder>at(x + background.width + 6, y + background.height - 56, -200)
                .scale(5)
                .render(ms);
    }

    @Override
    protected void init() {
        setWindowSize(background.width, background.height);
        setWindowOffset(-20, 0);
        super.init();

        int x = guiLeft;
        int y = guiTop;

        Label labelChannel = new Label(x + 49, y + 28, Components.immutableEmpty()).colored(0xFFFFFFFF)
                .withShadow();

        areaChannel = new SelectionScrollInput(x + 45, y + 21, 109, 18).forOptions(
                    List.of(Components.translatable("transmitter.network.1"), Components.translatable("transmitter.network.2"), Components.translatable("transmitter.network.3"), Components.translatable("transmitter.network.4"), Components.translatable("transmitter.network.5"), Components.translatable("transmitter.network.6"), Components.translatable("transmitter.network.7"), Components.translatable("transmitter.network.8"), Components.translatable("transmitter.network.9"), Components.translatable("transmitter.network.10"))
                )
                .titled(Lang.translateDirect("gui.transmitter.channel_title").plainCopy())
                .writingTo(labelChannel)
                .setState(te.getChannel());

        areaTestInput = new EditBox(font, x + 49, y + 50, 109, 18, Components.immutableEmpty());
        areaTestInput.setBordered(false);
        areaTestInput.setMaxLength(16);
        areaTestInput.setValue(te.getPassword());


        IconButton confirmButton = new IconButton(x + background.width - 33, y + background.height - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);

        addRenderableWidget(labelChannel);
        addRenderableWidget(areaChannel);
        addRenderableWidget(areaTestInput);


        addRenderableWidget(confirmButton);
    }

    @Override
    public void removed() {
        super.removed();
        te.getExtraCustomData().putInt("channel", areaChannel.getState());
        te.getExtraCustomData().putString("password", areaTestInput.getValue());
        TransmissionPackets.channel.sendToServer(new ConfigureTransmitterPacket(te.getBlockPos(), areaChannel.getState(), areaTestInput.getValue()));
    }
}
