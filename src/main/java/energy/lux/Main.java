package energy.lux;

import esdl.Area;
import esdl.EnergySystem;
import esdl.EsdlFactory;
import esdl.impl.EnergySystemImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import java.io.IOException;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        var factory = EsdlFactory.eINSTANCE;

        EnergySystem energySystem = EsdlFactory.eINSTANCE.createEnergySystem();
        energySystem.setName("My First EnergySystem");
        energySystem.setId("firstEnergySystem");

        var instances = energySystem.getInstance();
        var instance = factory.createInstance();
        instances.add(instance);

        Area area = EsdlFactory.eINSTANCE.createArea();
        instance.setArea(area);
        area.setId("Test");
        area.setName("Amsterdam municipality");

        var pvInstallation = factory.createPVInstallation();
        pvInstallation.setNumberOfPanels(42);
        area.getAsset().add(pvInstallation);

        var measure = EsdlFactory.eINSTANCE.createMeasure();
        var measures = EsdlFactory.eINSTANCE.createMeasures();

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