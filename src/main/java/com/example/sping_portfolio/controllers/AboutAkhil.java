package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.Getter;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class AboutAkhil {

    public List<_ePowers> eInit(int nth) {
        //Fibonacci objects created with different initializers
        List<_ePowers> eList = new ArrayList<>();
        eList.add(new eFor(nth));
        eList.add(new eWhile(nth));
        eList.add(new eRecurse(nth));
        eList.add(new eStream(nth));

        return eList;
    }

    @GetMapping("/aboutAkhil")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String akhil(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Model model,
            @RequestParam(name="number", required=false, defaultValue="1") int number) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("name", name); // MODEL is passed to html
        model.addAttribute("eList", eInit(number));
        model.addAttribute("number", number); // MODEL is passed to html
        return "aboutAkhil";
    }
}

class eFor extends _ePowers {

    public eFor(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        super.setStartTime();
        super.name = "For";
        int limit = super.size;
        double[] eForArray = new double[limit];
        eForArray[0] = (double) 1.0;
        for (int ii=1;ii<limit;ii++) {
            eForArray[ii] = (double) Math.exp(1.0) * eForArray[ii-1];
        }
        for (int jj=0;jj<limit;jj++) {
            super.setData(eForArray[jj]);
        }
        super.setEndTime();
    }
}

class eWhile extends _ePowers {

    public eWhile(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        super.setStartTime();
        super.name = "While";
        int limit = super.size;
        double[] eForArray = new double[limit];
        eForArray[0] = (double) 1.0;
        int ii = 1;
        while (ii<limit) {
            eForArray[ii] = (double) Math.exp(1.0) * eForArray[ii-1];
            ii++;
        }
        int jj = 0;
        while (jj<limit) {
            super.setData(eForArray[jj]);
            jj++;
        }
        super.setEndTime();
    }
}

class eRecurse extends _ePowers {
    private double initial;
    public eRecurse(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        super.setStartTime();
        super.name = "Recurse";
        int limit = super.size;
        this.initial = 1.0;
        initRecurse(limit);
    }
    public void initRecurse(int n) {
        if (n>0) {
            super.setData(initial);
            this.initial *= Math.exp(1.0);
            n--;
            initRecurse(n);
        }
        super.setEndTime();
    }
}

class eStream extends _ePowers {
    public eStream(int nth) {
        super(nth);
    }

    @Override
    protected void init() {
        super.setStartTime();
        super.name = "Stream";
        int limit = super.size;
        Stream.iterate(0, n -> n+1)
                .limit(limit)
                .forEach(x -> super.setData(Math.exp(x)));
        super.setEndTime();
    }
}


@Getter
abstract class _ePowers {
    private long start;
    private long end;
    int size;
    String name;
    ArrayList<Double> list;

    public _ePowers() {
        this( 20);
    }

    public _ePowers(int nth) {
        this.size = nth;
        this.list = new ArrayList<>();
        this.init();
    }

    protected abstract void init();

    public void setData(double num) {
        list.add(num);
    }

    public void setStartTime() {
        this.start = System.nanoTime();
    }

    public void setEndTime() {
        this.end = System.nanoTime();
    }

    public long getTimeElapsed() {
        return this.end - this.start;
    }

    public double getNth() {
        return list.get(size - 1);
    }
}
