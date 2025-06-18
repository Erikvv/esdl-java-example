package energy.lux;

import esdl.Area;
import esdl.EnergySystem;
import esdl.EsdlFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import java.io.IOException;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        var esdlFactory = EsdlFactory.eINSTANCE;

        EnergySystem energySystem = esdlFactory.createEnergySystem();
        energySystem.setName("My First EnergySystem");
        energySystem.setId("firstEnergySystem");

        var instances = energySystem.getInstance();
        var instance = esdlFactory.createInstance();
        instances.add(instance);

        Area area = esdlFactory.createArea();
        instance.setArea(area);
        area.setId("Test");
        area.setName("Amsterdam municipality");

        var pvInstallation = esdlFactory.createPVInstallation();
        pvInstallation.setNumberOfPanels(42);
        area.getAsset().add(pvInstallation);

        var measure = esdlFactory.createMeasure();
        var measures = esdlFactory.createMeasures();

        energySystem.setMeasures(measures);

        saveESDLModel(energySystem, "test.xmi");
    }

    public static XMIResource saveESDLModel(EnergySystem energySystem, String fileName) throws IOException, IOException {
        XMIResource resource = new XMIResourceImpl(URI.createURI(fileName));
        resource.getContents().add(energySystem);
        HashMap<String, Object> opts = new HashMap<String, Object>();
        // Produce an xsi:schemaLocation in the resource
        opts.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
        resource.save(opts);
        return resource;
    }
}