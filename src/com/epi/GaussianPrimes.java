package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class GaussianPrimes {
    // @include
    public static class Complex implements Comparable<Complex>{
        private int real;
        private int imag;

        public Complex(int real, int imag) {
            this.real = real;
            this.imag = imag;
        }

        public int getReal() {
            return real;
        }

        public int getImag() {
            return imag;
        }

        public int getNorm() {
            return real * real + imag * imag;
        }

        public Complex multiply(Complex p) {
            return new Complex(real * p.getReal() - imag * p.getImag(), real * p.getImag() + imag * p.getReal());
        }

        @Override
        public int compareTo(Complex o) {
            int result = Integer.valueOf(getNorm()).compareTo(o.getNorm());
            if(result == 0) {
                result = Integer.valueOf(getReal()).compareTo(o.getReal());
            }
            if(result == 0) {
                result = Integer.valueOf(getImag()).compareTo(o.getImag());
            }
            return result;
        }

        @Override
        public String toString() {
            return "(" + real + "," + imag + ")";
        }
    }

    public static List<Complex> generate_Gaussian_primes(int n) {
        TreeSet<Complex> candidates = new TreeSet<Complex>();
        ArrayList<Complex> primes = new ArrayList<Complex>();

        // Generate all possible Gaussian prime candidates.
        for (int i = -n; i <= n; ++i) {
            for (int j = -n; j <= n; ++j) {
                if (!is_unit(new Complex(i, j)) && i != 0 && j != 0) {
                    candidates.add(new Complex(i, j));
                }
            }
        }

        while (!candidates.isEmpty()) {
            Complex p = candidates.pollFirst();
            primes.add(p);
            int max_multiplier = (int) Math.ceil(Math.sqrt(2.0)*n/Math.floor(Math.sqrt(p.getNorm())));

            // Any Gaussian integer outside the range we're iterating
            // over below has a modulus greater than max_multiplier.
            for (int i = max_multiplier; i >= -max_multiplier; --i) {
                for (int j = max_multiplier; j >= -max_multiplier; --j) {
                    Complex x = new Complex(i, j);
                    if (Math.floor(Math.sqrt(x.getNorm())) > max_multiplier) {
                        // Skip multipliers whose modulus exceeds max_multiplier.
                        continue;
                    }
                    if (!is_unit(x)) {
                        candidates.remove(x.multiply(p));
                    }
                }
            }
        }
        return primes;
    }

    private static boolean is_unit(Complex z) {
        return (z.getReal() == 1 && z.getImag() == 0) ||
                (z.getReal() == -1 && z.getImag() == 0) ||
                (z.getReal() == 0 && z.getImag() == 1) ||
                (z.getReal() == 0 && z.getImag() == -1);
    }
    // @exclude

    private static List<Complex> generate_Gaussian_primes_canary(int n) {
        TreeSet<Complex> candidates = new TreeSet<Complex>();
        ArrayList<Complex> primes = new ArrayList<Complex>();

        // Generate all possible Gaussian prime candidates.
        for (int i = -n; i <= n; ++i) {
            for (int j = -n; j <= n; ++j) {
                if (!is_unit(new Complex(i, j)) && i != 0 && j != 0) {
                    candidates.add(new Complex(i, j));
                }
            }
        }

        while (!candidates.isEmpty()) {
            Complex p = candidates.pollFirst();
            primes.add(p);
            int max_multiplier = n;

            for (int i = max_multiplier; i >= -max_multiplier; --i) {
                for (int j = max_multiplier; j >= -max_multiplier; --j) {
                    Complex x = new Complex(i, j);
                    if (!is_unit(x)) {
                        candidates.remove(x.multiply(p));
                    }
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int n;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else {
            n = r.nextInt(100) + 1;
        }

        for (int i = 1; i <= 100; ++i) {
            System.out.println("n = " + i);
            List<Complex> first = generate_Gaussian_primes_canary(i);
            List<Complex> g_primes = generate_Gaussian_primes(i);
            System.out.println(first.size() + " " + g_primes.size());
            for (int j = 0; j < first.size(); ++j) {
                if (first.get(j).getReal() != g_primes.get(j).getReal() ||
                        first.get(j).getImag() != g_primes.get(j).getImag()) {
                    System.out.print(first.get(j) + " ");
                    System.out.print(g_primes.get(j) + " ");
                }
            }
            for (int j = first.size(); j < g_primes.size(); ++j) {
                System.out.print(g_primes.get(i) + " ");
            }
            assert(first.size() == g_primes.size());
        }
    }
}
