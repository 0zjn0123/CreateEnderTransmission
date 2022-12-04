package net.forsteri.createendertransmission.transmitUtil;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
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

    public KineticTileEntity te;

    public ItemStack renderedItem;

    public TransmitterScreen(KineticTileEntity te, ItemStack renderedItem) {
        super(Lang.translateDirect("gui.sequenced_gearshift.title"));
        this.te = te;
        this.renderedItem = renderedItem;
    }

    private final AllGuiTextures background = AllGuiTextures.WAND_OF_SYMMETRY;
    private IconButton confirmButton;

    private Label labelChannel;

    private Label labelPassword;

    private ScrollInput areaChannel;

    private ScrollInput areaPassword;

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
        labelPassword = new Label(x + 49, y + 50, Components.immutableEmpty()).colored(0xFFFFFFFF)
                .withShadow();

        areaChannel = new SelectionScrollInput(x + 45, y + 21, 109, 18).forOptions(
                    List.of(
                            Components.translatable("enchantment.level.1"),
                            Components.translatable("enchantment.level.2"),
                            Components.translatable("enchantment.level.3"),
                            Components.translatable("enchantment.level.4"),
                            Components.translatable("enchantment.level.5"),
                            Components.translatable("enchantment.level.6"),
                            Components.translatable("enchantment.level.7"),
                            Components.translatable("enchantment.level.8"),
                            Components.translatable("enchantment.level.9"),
                            Components.translatable("enchantment.level.10")
                    )
                )
                .titled(Lang.translateDirect("gui.transmitter.channel_title").plainCopy())
                .writingTo(labelChannel)
                .setState(
                        te.getTileData().contains("channel") ? te.getTileData().getInt("channel") : 0
                );

        areaPassword = new SelectionScrollInput(x + 45, y + 43, 109, 18).forOptions(
                        List.of(
                                Components.translatable("enchantment.level.1"),
                                Components.translatable("enchantment.level.2"),
                                Components.translatable("enchantment.level.3"),
                                Components.translatable("enchantment.level.4"),
                                Components.translatable("enchantment.level.5"),
                                Components.translatable("enchantment.level.6"),
                                Components.translatable("enchantment.level.7"),
                                Components.translatable("enchantment.level.8"),
                                Components.translatable("enchantment.level.9"),
                                Components.translatable("enchantment.level.10")
                        )
                )
                .titled(Lang.translateDirect("gui.transmitter.password_title").plainCopy())
                .writingTo(labelPassword)
                .setState(
                        te.getTileData().contains("password") ? te.getTileData().getInt("password") : 0
                );

        confirmButton =
                new IconButton(x + background.width - 33, y + background.height - 24, AllIcons.I_CONFIRM);
        confirmButton.withCallback(this::onClose);

        addRenderableWidget(labelChannel);
        addRenderableWidget(areaChannel);
        addRenderableWidget(labelPassword);
        addRenderableWidget(areaPassword);


        addRenderableWidget(confirmButton);
    }

    @Override
    public void removed() {
        super.removed();
        Packets.channel.sendToServer(new ConfigureTransmitterPacket(te.getBlockPos(), areaChannel.getState(), areaPassword.getState()));
    }
}
