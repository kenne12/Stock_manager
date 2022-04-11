
-- Column: bord

-- ALTER TABLE public.journee DROP COLUMN bord;

ALTER TABLE public.journee ADD COLUMN bord double precision;
ALTER TABLE public.journee ALTER COLUMN bord SET DEFAULT 0;
