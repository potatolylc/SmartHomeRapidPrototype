package ioedata.analysis.controller;

import ioedata.analysis.utils.ID3Algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/analysis")
public class AnalysisController {
	

	@RequestMapping(value = "id3", method = RequestMethod.GET)
	@ResponseBody
	public String id3Analysis() {
		/*ID3Algorithm inst = new ID3Algorithm();
        inst.readARFF(new File("weather.nominal.arff"));
        inst.setDec("play");
        LinkedList<Integer> ll=new LinkedList<Integer>();
        for(int i=0;i<inst.attribute.size();i++){
            if(i!=inst.decatt)
                ll.add(i);
        }
        ArrayList<Integer> al=new ArrayList<Integer>();
        for(int i=0;i<inst.data.size();i++){
            al.add(i);
        }
        inst.buildDT("DecisionTree", "null", al, ll);
        inst.writeXML("dt_algorithm.xml");*/
		return new JSONObject().toString();
	}

}
