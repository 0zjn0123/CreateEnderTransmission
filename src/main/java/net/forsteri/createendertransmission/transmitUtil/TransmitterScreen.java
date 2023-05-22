package net.forsteri.createendertransmission.transmitUtil;

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
import net.forsteri.createendertransmission.entry.Packets;
import net.minecraft.world.item.ItemStack;

import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class TransmitterScreen extends AbstractSimiScreen {

    public KineticBlockEntity te;

    public ItemStack renderedItem;

    public TransmitterScreen(KineticBlockEntity te, ItemStack renderedItem) {
        super(Lang.translateDirect("gui.sequenced_gearshift.title"));
        this.te = te;
        this.renderedItem = renderedItem;
    }

    private final AllGuiTextures background = AllGuiTextures.WAND_OF_SYMMETRY;
    private IconButton confirmButton;

    private Label labelChannel;

    private Label labelTestInput;

    private ScrollInput areaChannel;

    private TextInputWidget areaTestInput;

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

        labelChannel = new Label(x + 49, y + 28, Components.immutableEmpty()).colored(0xFFFFFFFF)
                .withShadow();

        labelTestInput = new Label(x + 49, y + 50, Components.immutableEmpty()).colored(0xFFFFFFFF)
                .withShadow();

        areaChannel = new SelectionScrollInput(x + 45, y + 21, 109, 18).forOptions(
                    List.of(Components.translatable("transmitter.network.1"), Components.translatable("transmitter.network.2"), Components.translatable("transmitter.network.3"), Components.translatable("transmitter.network.4"), Components.translatable("transmitter.network.5"), Components.translatable("transmitter.network.6"), Components.translatable("transmitter.network.7"), Components.translatable("transmitter.network.8"), Components.translatable("transmitter.network.9"), Components.translatable("transmitter.network.10"))
                )
                .titled(Lang.translateDirect("gui.transmitter.channel_title").plainCopy())
                .writingTo(labelChannel)
                .setState(
                        te.getTileData().contains("channel") ? te.getTileData().getInt("channel") : 0
                );

        areaTestInput = new TextInputWidget(x + 45, y + 43, 109, 18)
                .writingTo(labelTestInput)
                .setState(
                        te.getTileData().contains("password") ? te.getTileData().getString("password") : ""
                );

        confirmButton =
                new IconButton(x + background.width - 33, y + background.height - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);

        addRenderableWidget(labelChannel);
        addRenderableWidget(areaChannel);
        addRenderableWidget(labelTestInput);
        addRenderableWidget(areaTestInput);


        addRenderableWidget(confirmButton);
    }

    @Override
    public void removed() {
        super.removed();
        Packets.channel.sendToServer(new ConfigureTransmitterPacket(te.getBlockPos(), areaChannel.getState(), areaTestInput.getState()));
    }

    @Override
    public boolean charTyped(char p_94683_, int p_94684_) {
        return super.charTyped(p_94683_, p_94684_) || areaTestInput.charTyped(p_94683_, p_94684_);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return areaTestInput.keyPressed(keyCode, scanCode, modifiers);
    }
}
