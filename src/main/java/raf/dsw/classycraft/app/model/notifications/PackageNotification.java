package raf.dsw.classycraft.app.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PackageNotification {

    String name;
    PackageNtfType type;

}
